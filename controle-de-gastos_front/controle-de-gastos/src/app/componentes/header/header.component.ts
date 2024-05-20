import { Component, EventEmitter, Input, Output } from '@angular/core';
import { IUsuario } from '../login/model/IUsuario';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {
  @Output() onAbrirLogin = new EventEmitter();
  @Input() usuario!: IUsuario;
  @Input() usuarioLogado: boolean = false;

  abrirMensagemLogout: boolean = false;

  abrirLogin(tipo: String): void {
    this.onAbrirLogin.emit(tipo);
  }

  logout(): void {
    this.abrirMensagemLogout = true;
  }

  logoutCancelar(): void {
    this.abrirMensagemLogout = false;
  }

  logoutConfirmar(): void {
    this.abrirMensagemLogout = false;

    if(this.usuario) {
      this.limparUsuario();
      this.usuarioLogado = !this.usuarioLogado;
      console.log(this.usuario);
      return;
    }

    this.usuarioLogado = false;
    console.log(this.usuario);
  }

  private limparUsuario(): void {
    this.usuario.id = '';
    this.usuario.nome = '';
    this.usuario.email = '';
    this.usuario.senha = '';
    this.usuario.status = false;
  }
}
