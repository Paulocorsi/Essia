import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Diretorio } from '../models/diretorio.models';


@Injectable({
  providedIn: 'root'
})
export class DiretorioService {

  constructor(private http: HttpClient) { }

  private apiUrl = 'http://localhost:8080/api/diretorios';

  getDiretorios(): Observable<Diretorio[]> {
    return this.http.get<Diretorio[]>(this.apiUrl).pipe();
  }

}
