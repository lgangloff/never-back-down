import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Program } from "../domain/program.model";
import { I18nValue } from "../../shared/domain/i18n.model";


@Injectable()
export class ProgramService {

  constructor(private http: HttpClient) { }

  
 
  findAll(search: string){
    return this.http.get<Program[]>("api/programs", {
        params: new HttpParams().set("query", search)
      }
    );
  }

  save(program:Program){
    
    return this.http.put<Program>("api/programs", program);
  }

  delete(program: Program){
    return this.http.delete("api/programs/" + program.id);
  }

  get(id){
    return this.http.get<Program>("api/programs/" + id);
  }

  
}
