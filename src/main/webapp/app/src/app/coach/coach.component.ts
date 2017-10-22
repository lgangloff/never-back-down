import { Component, OnInit } from '@angular/core';
import { CoachService } from './services/coach.service';
import { Coach } from './domain/coach.model';


@Component({
  selector: 'app-coach',
  templateUrl: './coach.component.html',
  styleUrls: ['./coach.component.css']
})
export class CoachComponent implements OnInit {

  private searchStatus;
  private error;
  private searchValue = "";

  coachs: Coach[];

  constructor(
    private service: CoachService) { }

  ngOnInit() {
    this.search();
  }

  search(){
    this.searchStatus = "wait";
    this.coachs = null;
    this.service.findAll(this.searchValue).subscribe(res =>{
      this.coachs = res;
      this.searchStatus = null;
    },
    err=>{
      this.searchStatus = null;
      this.error = err;
    });
  }

}
