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

  abrirLogin(tipo: String): void {
    this.onAbrirLogin.emit(tipo);
  }
}
