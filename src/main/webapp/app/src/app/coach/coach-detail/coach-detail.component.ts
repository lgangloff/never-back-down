import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import {Location} from '@angular/common';
import {CoachService} from '../services/coach.service';
import {Coach} from '../domain/coach.model';

@Component({
  selector: 'app-coach-detail',
  templateUrl: './coach-detail.component.html',
  styleUrls: ['./coach-detail.component.css']
})
export class CoachDetailComponent implements OnInit {

  private coach: Coach;
  private error;
  private status:string;

  
  constructor(
    private service: CoachService,
    private route: ActivatedRoute,
    private router: Router,
    private location: Location) { }


  ngOnInit(){
    let id = this.route.snapshot.paramMap.get('id');
    if (!id){
      this.coach = new Coach();
    }
    else{
      this.service.get(id).subscribe(c=>{
          this.coach = c;
        },
        err=>{
          this.router.navigate(["coach"]);
        }
      )
    }
  }


  onSubmit() {
    this.status = "wait";
    this.service.save(this.coach).subscribe(
      success=>{
        this.status = "success";
        setTimeout (() => {
          this.router.navigate(["coach"]);
        }, 1000)
      },
      e=>{
        this.status = "error";
        this.error = e.error;
      }
    );
    
  }

  delete(){
    if (confirm("Etes-vous sur de vouloir supprimer le coach ?")){
      this.service.delete(this.coach).subscribe(res=>{
        this.router.navigate(["coach"]);
      });
    }
  }

  cancel(){
    this.location.back();
  }

  competenceKeys(){
    let keys = this.coach.competenceI18nFields == null ? [] : Object.keys(this.coach.competenceI18nFields);
    return keys.sort();
  }

  addCompetence(){
    this.coach.competenceI18nFields["coach.competence."+(this.competenceKeys().length+1)] = [];
  }

  removeCompetenceAtIndex(key:string){
    delete this.coach.competenceI18nFields[key];
  }


}
