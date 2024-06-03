import { Component, EventEmitter, Input, Output } from '@angular/core';
import { IGasto } from '../../modal/gasto/IGasto';
import { ITipoGasto } from '../../modal/tipoGasto/ITipoGasto';

@Component({
  selector: 'app-lista-controle',
  templateUrl: './lista-controle.component.html',
  styleUrl: './lista-controle.component.scss'
})
export class ListaControleComponent {
  @Input() gastos: IGasto[] = [];
  @Input() tipo: String = '';
  @Output() onUpdateStatus = new EventEmitter();
  @Output() onDelete = new EventEmitter();

  constructor() {
    console.log(this.tipo);
  }

  updateStatus(id: String): void {
    this.onUpdateStatus.emit(id);
  }

  delete(id: String): void {
    this.onDelete.emit(id);
  }
}
