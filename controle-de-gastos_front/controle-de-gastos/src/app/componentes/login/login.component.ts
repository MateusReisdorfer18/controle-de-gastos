import { Component, EventEmitter, Input, Output } from '@angular/core';
import { UsuarioService } from './usuario.service';
import { IUsuario } from './model/IUsuario';
import { IUsuarioCadastro } from './model/IUsuarioCadastro';
import { IUsuarioAut } from './model/IUsuarioAut';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  @Input() tipo!: String;
  @Output() onVoltarHome = new EventEmitter();
  @Output() onEnviarUsuario = new EventEmitter();

  emailLogin: String = "";
  senhaLogin: String = "";
  nomeCadastro: String = "";
  emailCadastro: String = "";
  senhaCadastro: String = "";

  constructor(private service: UsuarioService) {}
  
  mudarTela(): void {
    if(this.tipo === 'entrar') {
      this.tipo = 'cadastro';
      this.limparInput();
    } else if (this.tipo === 'cadastro') {
      this.tipo = 'entrar';
      this.limparInput();
    }
  }

  voltarHome(): void {
    this.onVoltarHome.emit();
  }

  validarBotao(): boolean {
    if(this.tipo === 'entrar') 
      return this.validarBotaoEntrar();

    if(this.tipo === 'cadastro')
      return this.validarBotaoCadastro();

    return true;
  }

  limparInput(): void {
    if(this.tipo === 'entrar')
      this.limparInputEntrar();

    if(this.tipo === 'cadastro')
      this.limparInputCadastro();
  }

  enviarDados(): void {
    if(this.tipo === 'entrar') {
      this.enviarDadosEntrar();
      this.limparInputEntrar();
    }

    if(this.tipo === 'cadastro') {
      this.enviarDadosCadastro();
      this.mudarTela();
    }
  }

  private validarBotaoEntrar(): boolean {
    if((this.emailLogin === "") || (this.senhaLogin === ""))
      return false;
    else
      return true;
  }

  private validarBotaoCadastro(): boolean {
    if((this.nomeCadastro === "") || (this.emailCadastro === "") || (this.senhaCadastro === ""))
      return false;
    else
      return true;
  }

  private limparInputEntrar(): void {
    this.emailLogin = "";
    this.senhaLogin = "";
  }

  private limparInputCadastro(): void {
    this.nomeCadastro = "";
    this.emailCadastro = "";
    this.senhaCadastro = "";
  }

  private enviarDadosEntrar(): void {
    const usuario: IUsuarioAut = {
      email: this.emailLogin,
      senha: this.senhaLogin
    }

    this.service.autenticar(usuario).subscribe(
      (usuario) =>  {
        this.onEnviarUsuario.emit(usuario)
      }
    );
  }

  private enviarDadosCadastro(): void {
    const usuario: IUsuarioCadastro = {
      nome: this.nomeCadastro,
      email: this.emailCadastro,
      senha: this.senhaCadastro
    }

    this.service.create(usuario).subscribe((usuario) => console.log(usuario));
  }
}
