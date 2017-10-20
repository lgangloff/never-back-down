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

  private status;
  private error;

  private webSite: WebSiteConfig;

  constructor(private service: WebSiteService) {
  }

  ngOnInit() {
    this.search();
  }

  search(){
    this.service.get().subscribe(res =>{
      this.webSite = res;
      if (this.webSite.backgroundImageFile == null){
        this.webSite.backgroundImageFile = new File();
      }
      if (this.webSite.logo500ImageFile == null){
        this.webSite.logo500ImageFile = new File();
      }
      if (this.webSite.logo300ImageFile == null){
        this.webSite.logo300ImageFile = new File();
      }
    },
    err=>{
      this.error = err;
    });
  }

  
  onSubmit() {
    this.status = "wait";
    this.service.save(this.webSite).subscribe( res =>{
      this.status = "success";
      this.search();
      setTimeout (() => {
        window.scroll(0, 0);
      }, 1000)
    }, err=>{
      this.status = "error";
      this.error = err;
    });
  }

}
