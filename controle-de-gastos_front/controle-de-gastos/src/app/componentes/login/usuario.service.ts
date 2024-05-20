import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { IUsuario } from './model/IUsuario';
import { IUsuarioAut } from './model/IUsuarioAut';
import { IUsuarioCadastro } from './model/IUsuarioCadastro';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  constructor(private http: HttpClient) { }

  autenticar(usuario: IUsuarioAut): Observable<IUsuario> {
    return this.http.post<IUsuario>('/api/usuario', usuario);
  }

  findAll(): Observable<IUsuario[]> {
    return this.http.get<IUsuario[]>('/api/usuario');
  }

  findById(id: String): Observable<IUsuario> {
    return this.http.get<IUsuario>(`/api/usuario/${id}`);
  }

  create(usuario: IUsuarioCadastro): Observable<IUsuario> {
    return this.http.post<IUsuario>('/api/usuario/cadastrar', usuario);
  }

  alter(usuario: IUsuarioCadastro, id: String): Observable<IUsuario> {
    return this.http.put<IUsuario>(`/api/usuario/alterar/${id}`, usuario);
  }

  delete(id: String): Observable<Boolean> {
    return this.http.delete<Boolean>(`/api/usuario/excluir/${id}`);
  }
}
