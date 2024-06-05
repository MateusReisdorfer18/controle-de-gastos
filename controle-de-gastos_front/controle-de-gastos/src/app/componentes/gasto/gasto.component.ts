import { Component, EventEmitter, Input, Output } from '@angular/core';
import { IGasto } from '../../modal/gasto/IGasto';
import { GastoDTO } from '../../modal/gasto/GastoDTO';

@Component({
  selector: 'app-gasto',
  templateUrl: './gasto.component.html',
  styleUrl: './gasto.component.scss'
})
export class GastoComponent {
  @Input() gasto!: IGasto;
  @Output() onAlter = new EventEmitter();
  @Output() onUpdateStatus = new EventEmitter();
  @Output() onDelete = new EventEmitter();
  editarGasto: Boolean = false;

  abrirEditar(): void {
    this.editarGasto = true;
  }

  fecharEditar(): void {
    this.editarGasto = false;
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
