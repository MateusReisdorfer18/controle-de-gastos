import { Component, EventEmitter, Output } from '@angular/core';
import { IUsuario } from './componentes/login/model/IUsuario';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  login: boolean = false;
  telaLogin!: String;
  usuario!: IUsuario;
  usuarioLogado: boolean = false;

  abrirLogin(tipo: String): void {
    this.telaLogin = tipo;
    this.login = !this.login;
  }

  voltarHome(): void {
    this.login = !this.login;
  }

  receberUsuario(usuario: IUsuario): void {
    this.usuario = usuario;
    this.usuarioLogado = !this.usuarioLogado;
    this.login = !this.login;
  }
}
