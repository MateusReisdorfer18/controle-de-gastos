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
  @Output() onFindAll = new EventEmitter();
  @Output() onUpdateStatus = new EventEmitter();
  @Output() onDelete = new EventEmitter();
  @Output() onCreate = new EventEmitter();
  @Output() onAlter = new EventEmitter();
  gastoDTO: GastoDTO = new GastoDTO();
  gastosFiltrados!: IGasto[];
  tipoFiltro!: String;
  dataFiltro!: String;
  cadastrarGasto: Boolean = false;
  editarGasto: Boolean = false;
  filtrarGastos: Boolean = false; 

  abrirCadastro(): void {
    this.cadastrarGasto = true;
  }

  abrirEditar(): void {
    this.editarGasto = true;
  }

  abrirFiltros(): void {
    this.filtrarGastos = true;
  }

  fecharCadastro(): void {
    this.cadastrarGasto = false;
    this.fecharFiltros();
  }

  fecharEditar(): void {
    this.editarGasto = false;
  }

  fecharFiltros(): void {
    this.filtrarGastos = false;
    this.dataFiltro = '';
    this.tipoFiltro = '';
  }

  findAll(): void {
    this.onFindAll.emit();
    this.fecharFiltros();
  }

  filtrar(): void {
    const tipoFiltro: Number = Number(this.tipoFiltro);
    const dataFiltro: Number = Number(this.dataFiltro);

    this.gastos = this.filtro(tipoFiltro, dataFiltro);

    this.fecharFiltros();
  }

  private filtro(tipoFiltro: Number, dataFiltro: Number): IGasto[] {
    let gastosFiltrados: IGasto[] = [];

    this.gastos.forEach((gasto) => {
      if(tipoFiltro === 1) {
       const returnGasto: IGasto | null = this.filtroDia(dataFiltro, gasto); 

       if(returnGasto)
        gastosFiltrados.push(gasto);
      }

      if(tipoFiltro === 2) {
        const returnGasto: IGasto | null = this.filtroMes(dataFiltro, gasto);

        if(returnGasto)
          gastosFiltrados.push(gasto);
      }

      if(tipoFiltro === 3) {
        const returnGasto: IGasto | null = this.filtroAno(dataFiltro, gasto);

        if(returnGasto)
          gastosFiltrados.push(gasto);
      }
    });

    return gastosFiltrados;
  }

  private filtroDia(dataFiltro: Number, gasto: IGasto): IGasto | null {
    if((gasto.dataCriacao.getDate() + 1) === dataFiltro)
      return gasto;

    return null;
  }

  private filtroMes(dataFiltro: Number, gasto: IGasto): IGasto | null {
    if((gasto.dataCriacao.getMonth() + 1) === dataFiltro)
      return gasto;

    return null;
  }

  private filtroAno(dataFiltro: Number, gasto: IGasto): IGasto | null {
    if(gasto.dataCriacao.getFullYear() === dataFiltro)
      return gasto;

    return null;
  }

  create(): void {
    this.gastoDTO.tipo = this.tipo;
    this.onCreate.emit(this.gastoDTO);
    this.fecharCadastro();
    this.limparInput();
  }

  alter(gasto: GastoDTO): void {
    this.onAlter.emit(gasto);
  }

  updateStatus(id: String): void {
    this.onUpdateStatus.emit(id);
  }

  delete(id: String): void {
    this.onDelete.emit(id);
  }

  private limparInput(): void {
    this.gastoDTO.gasto = '';
    this.gastoDTO.local = '';
    this.gastoDTO.preco = 0;
  }
}
