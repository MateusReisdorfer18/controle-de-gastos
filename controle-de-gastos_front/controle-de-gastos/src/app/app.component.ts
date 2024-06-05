import { Component, OnChanges, SimpleChanges } from '@angular/core';
import { GastoService } from './modal/gasto/gasto.service';
import { IGasto } from './modal/gasto/IGasto';
import { ITipoGasto } from './modal/tipoGasto/ITipoGasto';
import { TipoGastoService } from './modal/tipoGasto/tipo-gasto.service';
import { GastoDTO } from './modal/gasto/GastoDTO';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {

  gastosCasa: IGasto[] = [];
  gastosMercado: IGasto[] = [];
  gastosOutros: IGasto[] = [];
  tipoCasa: any = {};
  tipoMercado: any = {};
  tipoOutros: any = {};

  constructor(private gastoService: GastoService, private tipoGastoService: TipoGastoService) {
    this.findAll();
    this.popularTipos();
  }

  findAll(): void {
    this.limparListas();
    this.gastoService.getAll().subscribe((gastos) => {
      this.popularListas(gastos);
    });
  }

  create(gasto: GastoDTO): void {
    this.gastoService.create(gasto).subscribe(() => this.findAll());
  }

  alter(gasto: GastoDTO): void {
    this.gastoService.alter(gasto, gasto.id).subscribe(() => this.findAll());
  }

  updateStatus(id: String): void {
    this.gastoService.updateStatus(id).subscribe(() => this.findAll());
  }

  delete(id: String): void {
    this.gastoService.delete(id).subscribe(() => this.findAll());
  }

  private limparListas(): void {
    this.gastosCasa = [];
    this.gastosMercado = [];
    this.gastosOutros = [];
  }

  private popularListas(gastos: IGasto[]): void {
    gastos.forEach((gasto) => {
      if(gasto.tipo.tipo === "Casa") {
        gasto.dataCriacao = new Date(gasto.dataCriacao);
        this.gastosCasa.push(gasto);
      }

      if(gasto.tipo.tipo === "Mercado") {
        gasto.dataCriacao = new Date(gasto.dataCriacao);
        this.gastosMercado.push(gasto);
      }

      if(gasto.tipo.tipo === "Outros") {
        gasto.dataCriacao = new Date(gasto.dataCriacao);
        this.gastosOutros.push(gasto);
      }
    })
  }

  private popularTipos(): void {
    this.tipoGastoService.getByTipo("Casa").subscribe((tipoGasto) => {
      this.tipoCasa = tipoGasto;
    });

    this.tipoGastoService.getByTipo("Mercado").subscribe((tipoGasto) => {
      this.tipoMercado = tipoGasto;
    });

    this.tipoGastoService.getByTipo("Outros").subscribe((tipoGasto) => {
      this.tipoOutros = tipoGasto;
    });
  }
}

