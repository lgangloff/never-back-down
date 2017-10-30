import { Component, OnInit } from '@angular/core';
import { ProgramService } from './services/program.service';
import { Program } from './domain/program.model';


@Component({
  selector: 'app-program',
  templateUrl: './program.component.html',
  styleUrls: ['./program.component.css']
})
export class ProgramComponent implements OnInit {

  private searchStatus;
  private error;
  private searchValue = "";

  programs: Program[];

  constructor(
    private service: ProgramService) { }

  ngOnInit() {
    this.search();
  }

  search(){
    this.searchStatus = "wait";
    this.programs = null;
    this.service.findAll(this.searchValue).subscribe(res =>{
      this.programs = res;
      this.searchStatus = null;
    },
    err=>{
      this.searchStatus = null;
      this.error = err;
    });
  }

}
