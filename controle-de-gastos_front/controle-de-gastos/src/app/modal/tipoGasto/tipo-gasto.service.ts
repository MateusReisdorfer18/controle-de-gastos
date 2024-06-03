import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ITipoGasto } from './ITipoGasto';

@Injectable({
  providedIn: 'root'
})
export class TipoGastoService {
  private URL: String = 'http://localhost:8080/tipo-gasto'

  constructor(private http: HttpClient) { }

  getByTipo(tipo: String): Observable<ITipoGasto> {
    return this.http.get<ITipoGasto>(`${this.URL}/tipo/${tipo}`);
  }
}
