import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import {Location} from '@angular/common';
import {FileService} from '../../services/file.service';
import {File} from '../../shared/domain/file.model';

@Component({
  selector: 'app-file-detail',
  templateUrl: './file-detail.component.html',
  styleUrls: ['./file-detail.component.css']
})
export class FileDetailComponent implements OnInit {
  
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
    let id = this.route.snapshot.paramMap.get('id');
    if (!id){
      this.file = new File();
    }
    else{
      this.service.get(id).subscribe(file=>{
          this.file = file;
        },
        err=>{
          this.router.navigate(["files"]);
        }
      )
    }
  }


  onSubmit() {

    let fileBrowser = this.fileInput.nativeElement;
    if (fileBrowser.files && fileBrowser.files[0]) {
      
      const formData = new FormData();
      formData.append("file", fileBrowser.files[0]);

      this.service.store(formData).subscribe(
        success=>{
          this.status = "success";
          setTimeout (() => {
            this.router.navigate(["files"]);
          }, 1000)
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
    if (confirm("Etes-vous sur de vouloir supprimer le fichier ?")){
      /*this.service.delete(this.user).subscribe(res=>{
        this.router.navigate(["users"]);
      });*/
    }
  }

  cancel(){
    this.location.back();
  }
}
