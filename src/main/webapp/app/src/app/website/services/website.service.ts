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


  getI18n(){
    return this.http.get<Map<String, Map<String, I18nValue>>>("api/website/i18n");
  }

  
}
