import {
  Component,
  AfterViewInit,
  EventEmitter,
  OnDestroy,
  Input,
  Output
} from '@angular/core';

import 'tinymce';
import 'tinymce/themes/modern';

import 'tinymce/plugins/textcolor';
import 'tinymce/plugins/colorpicker';
import 'tinymce/plugins/table';
import 'tinymce/plugins/link';

declare var tinymce: any;

@Component({
  selector: 'tiny-editor',
  template: `<textarea id="{{elementId}}"></textarea>`
})
export class TinyEditorComponent implements AfterViewInit, OnDestroy {
  @Input() elementId: String;
        
    @Input() value: String;
    @Output() valueChange = new EventEmitter();
 
  editor;
 
  ngAfterViewInit() {
    tinymce.init({
      selector: '#' + this.elementId,
      menubar: false,
      plugins: ['textcolor', 'colorpicker'],
      toolbar: 'insert | undo redo |  formatselect | bold italic forecolor backcolor  | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | removeformat | help',
      skin_url: 'assets/skins/lightgray',
      setup: editor => {
        this.editor = editor;
        
        if(this.value && editor){
            
            editor.on("init", ed => {                    
                ed.target.setContent(this.value);                    
            }); 
            
        }
        
        editor.on('keyup change', () => {
          const content = editor.getContent();
          this.valueChange.emit(content);
        });
      }
    });
  }

  ngOnDestroy() {
    tinymce.remove(this.editor);
  }
}