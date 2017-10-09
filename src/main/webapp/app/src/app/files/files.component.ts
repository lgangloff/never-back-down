import { Component, OnInit } from '@angular/core';
import {FileService} from '../services/file.service';
import {File} from '../shared/domain/file.model';

@Component({
  selector: 'app-files',
  templateUrl: './files.component.html',
  styleUrls: ['./files.component.css']
})
export class FilesComponent implements OnInit {

  private searchStatus;
  private error;
  private searchValue = "";

  files: File[];

  constructor(
    private service: FileService) { }

  ngOnInit() {
    this.search();
  }

  search(){
    this.searchStatus = "wait";
    this.files = null;
    this.service.findAll(this.searchValue).subscribe(res =>{
      this.files = res;
      this.searchStatus = null;
    },
    err=>{
      this.searchStatus = null;
      this.error = err;
    });
  }

}
