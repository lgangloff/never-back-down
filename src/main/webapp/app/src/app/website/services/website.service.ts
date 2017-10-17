import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { WebSiteConfig } from "../domain/website.model";
import { I18nValue } from "../../shared/domain/i18n.model";


@Injectable()
export class WebSiteService {

  constructor(private http: HttpClient) { }

  
  get(){
    return this.http.get<WebSiteConfig>("api/website");
  }


  save(config:WebSiteConfig){
    if (config.logo300ImageFile != null && config.logo300ImageFile.id == null){
      config.logo300ImageFile = null;
    }
    if (config.logo500ImageFile != null && config.logo500ImageFile.id == null){
      config.logo500ImageFile = null;
    }
    if (config.backgroundImageFile != null && config.backgroundImageFile.id == null){
      config.backgroundImageFile = null;
    }
    return this.http.put<WebSiteConfig>("api/website", config);
  }

  
}
