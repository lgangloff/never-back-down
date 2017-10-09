import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { File } from "../shared/domain/file.model";


@Injectable()
export class FileService {

  constructor(private http: HttpClient) { }


  store(formData: FormData){
    return this.http.post<File>("api/files", formData,{
      headers: new HttpHeaders()
    })
  }

  delete(file: File){
    return this.http.delete("api/files/" + file.id);
  }

  get(id){
    return this.http.get<File>("api/files/" + id);
  }

  findAll(search: string){
    return this.http.get<File[]>("api/files", {
        params: new HttpParams().set("query", search)
      }
    );
  }
}
