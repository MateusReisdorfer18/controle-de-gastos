import { Component, EventEmitter, Input, Output } from '@angular/core';
import { IGasto } from '../../modal/gasto/IGasto';
import { ITipoGasto } from '../../modal/tipoGasto/ITipoGasto';
import { GastoDTO } from '../../modal/gasto/GastoDTO';

@Component({
  selector: 'app-lista-controle',
  templateUrl: './lista-controle.component.html',
  styleUrl: './lista-controle.component.scss'
})
export class ListaControleComponent {
  @Input() gastos: IGasto[] = [];
  @Input() tipo!: ITipoGasto;
  @Output() onUpdateStatus = new EventEmitter();
  @Output() onDelete = new EventEmitter();
  @Output() onCreate = new EventEmitter();
  @Output() onAlter = new EventEmitter();
  cadastrarGasto: Boolean = false;
  editarGasto: Boolean = false;
  gastoDTO: GastoDTO = new GastoDTO();

  constructor() {
    console.log(this.tipo);
  }

  abrirCadastro(): void {
    this.cadastrarGasto = true;
  }

  abrirEditar(): void {
    this.editarGasto = true;
  }

  fecharEditar(): void {
    this.editarGasto = false;
  }

  fecharCadastro(): void {
    this.cadastrarGasto = false;
  }

  create(): void {
    this.gastoDTO.tipo = this.tipo;
    this.onCreate.emit(this.gastoDTO);
    this.fecharCadastro();
  }

  alter(gasto: GastoDTO, id: String): void {
    gasto.id = id;
    this.onAlter.emit(gasto);
    this.fecharEditar();
  }

  updateStatus(id: String, status: Boolean): void {
    if(status)
      return;
    
    this.onUpdateStatus.emit(id);
  }

  delete(id: String): void {
    this.onDelete.emit(id);
  }
}
