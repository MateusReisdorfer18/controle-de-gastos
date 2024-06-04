import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IGasto } from './IGasto';
import { GastoDTO } from './GastoDTO';

@Injectable({
  providedIn: 'root'
})
export class GastoService {
  private URL: String = 'http://localhost:8080/gasto'

  constructor(private http: HttpClient) { }

  getAll(): Observable<IGasto[]> {
    return this.http.get<IGasto[]>(`${this.URL}`);
  }

  getById(id: String): Observable<IGasto> {
    return this.http.get<IGasto>(`${this.URL}/${id}`);
  }

  create(gasto: GastoDTO): Observable<IGasto> {
    return this.http.post<IGasto>(`${this.URL}/cadastrar`, gasto);
  }

  alter(gasto: GastoDTO, id: String): Observable<IGasto> {
    return this.http.put<IGasto>(`${this.URL}/alterar/${id}`, gasto);
  }

  updateStatus(id: String): Observable<Boolean> {
    return this.http.patch<Boolean>(`${this.URL}/alterar/status/${id}`, {});
  }

  delete(id: String): Observable<Boolean> {
    return this.http.delete<Boolean>(`${this.URL}/excluir/${id}`);
  }
}
