import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Coach } from "../domain/coach.model";
import { I18nValue } from "../../shared/domain/i18n.model";


@Injectable()
export class CoachService {

  constructor(private http: HttpClient) { }

  
 
  findAll(search: string){
    return this.http.get<Coach[]>("api/coach", {
        params: new HttpParams().set("query", search)
      }
    );
  }

  save(coach:Coach){
    if (coach.photo != null && coach.photo.id == null){
      coach.photo = null;
    }
    
    return this.http.put<Coach>("api/coach", coach);
  }

  
}
