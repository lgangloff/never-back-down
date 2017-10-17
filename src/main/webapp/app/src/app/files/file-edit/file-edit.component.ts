import { Component, OnInit, ViewChild, Input } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import {Location} from '@angular/common';
import {FileService} from '../../services/file.service';
import {File} from '../../shared/domain/file.model';

@Component({
  selector: 'file-edit',
  templateUrl: './file-edit.component.html',
  styleUrls: ['./file-edit.component.css']
})
export class FileEditComponent implements OnInit {
    
  @Input("file")
  private file = new File();
  private error;
  private status:string;

  @ViewChild('fileInput') fileInput;
  
  
  constructor(
    private service: FileService,
    private route: ActivatedRoute,
    private router: Router,
    private location: Location) { }


  ngOnInit(){
  }


  onSubmit() {

    let fileBrowser = this.fileInput.nativeElement;
    if (fileBrowser.files && fileBrowser.files[0]) {
      
      const formData = new FormData();
      formData.append("file", fileBrowser.files[0]);

      this.service.store(formData).subscribe(
        success=>{
          this.status = "success";
          this.file.id = success.id;
          this.file.contentType = success.contentType;
          this.file.name = success.name;
          this.file.uuid = success.uuid;
          this.file.size = success.size;
        },
        e=>{
          this.status = "error";
          this.error = e.error;
        }
      );
    }
    this.status = "wait";
    
  }

  delete(){
    this.file.id = null;
    this.file.contentType = null;
    this.file.name = null;
    this.file.uuid = null;
    this.file.size = null;
  }

  cancel(){
    this.location.back();
  }
}