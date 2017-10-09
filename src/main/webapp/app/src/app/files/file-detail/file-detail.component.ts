import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import {Location} from '@angular/common';
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
  
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private location: Location) { }

  ngOnInit() {
  }


  onSubmit() {
    this.status = "wait";
    /*
    this.service.save(this.file).subscribe(
      success=>{
        this.status = "success";
        setTimeout (() => {
          this.router.navigate(["users"]);
        }, 1000)
      },
      e=>{
        this.status = "error";
        this.error = e.error;
      }
    );*/
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
