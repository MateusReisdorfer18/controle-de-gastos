import { Component, OnChanges, SimpleChanges } from '@angular/core';
import { GastoService } from './modal/gasto/gasto.service';
import { IGasto } from './modal/gasto/IGasto';
import { ITipoGasto } from './modal/tipoGasto/ITipoGasto';
import { TipoGastoService } from './modal/tipoGasto/tipo-gasto.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {

  gastosCasa: IGasto[] = [];
  gastosFerramentas: IGasto[] = [];
  gastosEletronicos: IGasto[] = [];
  tipoCasa!: ITipoGasto;
  tipoFerramentas!: ITipoGasto;
  tipoEletronicos!: ITipoGasto;

  constructor(private gastoService: GastoService, private tipoGastoService: TipoGastoService) {
    this.findAll();
    this.popularTipos();
  }

  findAll(): void {
    this.gastoService.getAll().subscribe((gastos) => {
      this.popularListas(gastos);
    });
  }

  updateStatus(id: String): void {
    this.gastoService.updateStatus(id).subscribe();
  }

  delete(id: String): void {
    this.gastoService.delete(id).subscribe();
  }

  private popularListas(gastos: IGasto[]): void {
    gastos.forEach((gasto) => {
      if(gasto.tipo.tipo === "casa")
        this.gastosCasa.push(gasto);

      if(gasto.tipo.tipo === "ferramentas")
        this.gastosFerramentas.push(gasto);

      if(gasto.tipo.tipo === "eletronicos")
        this.gastosEletronicos.push(gasto);
    })
  }

  private popularTipos(): void {
    this.tipoGastoService.getByTipo("ferramentas").subscribe((tipoGasto) => {
      this.tipoFerramentas = tipoGasto;
    });

    this.tipoGastoService.getByTipo("eletronicos").subscribe((tipoGasto) => {
      this.tipoEletronicos = tipoGasto;
    });

    this.tipoGastoService.getByTipo("casa").subscribe((tipoGasto) => {
      this.tipoCasa = tipoGasto;
    });
  }
}

