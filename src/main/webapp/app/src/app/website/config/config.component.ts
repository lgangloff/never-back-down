import { Component, OnInit } from '@angular/core';
import {WebSiteService} from '../services/website.service';
import {WebSiteConfig} from '../domain/website.model';
import {I18nValue} from '../../shared/domain/i18n.model';
import {File} from '../../shared/domain/file.model';

@Component({
  selector: 'app-config',
  templateUrl: './config.component.html',
  styleUrls: ['./config.component.css']
})
export class ConfigComponent implements OnInit {

  private searchStatus;
  private error;

  private webSite: WebSiteConfig;
  private i18nValues: Map<String, I18nValue[]>;

  constructor(
    private service: WebSiteService) {
  }

  ngOnInit() {
    this.search();
  }

  search(){
    this.searchStatus = "wait";
    this.service.get().subscribe(res =>{
      this.webSite = res;
      if (this.webSite.backgroundImageFile == null){
        this.webSite.backgroundImageFile = new File();
      }
      this.service.getI18n().subscribe(res=>{
        this.i18nValues = res;
        this.searchStatus = null;
      })
    },
    err=>{
      this.searchStatus = null;
      this.error = err;
    });
  }

  
  onSubmit() {
    console.log(this.webSite);
    console.log(this.i18nValues);
  }

}
