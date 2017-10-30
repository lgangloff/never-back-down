import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import {Location} from '@angular/common';
import {ProgramService} from '../services/program.service';
import {Program} from '../domain/program.model';
import { File } from '../../shared/domain/file.model'

@Component({
  selector: 'app-program-detail',
  templateUrl: './program-detail.component.html',
  styleUrls: ['./program-detail.component.css']
})
export class ProgramDetailComponent implements OnInit {

  private program: Program;
  private error;
  private status:string;

  
  constructor(
    private service: ProgramService,
    private route: ActivatedRoute,
    private router: Router,
    private location: Location) { }


  ngOnInit(){
    let id = this.route.snapshot.paramMap.get('id');
    if (!id){
      this.program = new Program();
    }
    else{
      this.service.get(id).subscribe(c=>{
          this.program = c;
        },
        err=>{
          this.router.navigate(["program"]);
        }
      )
    }
  }


  onSubmit() {
    this.status = "wait";
    this.service.save(this.program).subscribe(
      success=>{
        this.status = "success";
        setTimeout (() => {
          this.router.navigate(["program"]);
        }, 1000)
      },
      e=>{
        this.status = "error";
        this.error = e.error;
      }
    );
    
  }

  delete(){
    if (confirm("Etes-vous sur de vouloir supprimer le program ?")){
      this.service.delete(this.program).subscribe(res=>{
        this.router.navigate(["program"]);
      });
    }
  }

  cancel(){
    this.location.back();
  }

}
