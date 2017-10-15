import { Component, OnInit } from '@angular/core';
import {WebSiteService} from '../services/website.service';
import {WebSiteConfig} from '../domain/website.model';
import {I18nValue} from '../../shared/domain/i18n.model';

@Component({
  selector: 'app-config',
  templateUrl: './config.component.html',
  styleUrls: ['./config.component.css']
})
export class ConfigComponent implements OnInit {

  private searchStatus;
  private error;

  webSite: WebSiteConfig;
  i18nValues: Map<String, Map<String, I18nValue>>;

  constructor(
    private service: WebSiteService) { }

  ngOnInit() {
    this.search();
  }

  search(){
    this.searchStatus = "wait";
    this.service.get().subscribe(res =>{
      this.webSite = res;
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

  i18nKeys() : Array<string> {
    return Object.keys(this.i18nValues);
  }

  
  onSubmit() {
    console.log(this.i18nValues);
  }

}
