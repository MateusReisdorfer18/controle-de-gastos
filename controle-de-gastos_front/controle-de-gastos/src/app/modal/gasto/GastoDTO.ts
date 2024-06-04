import { ITipoGasto } from "../tipoGasto/ITipoGasto";

export class GastoDTO {
    id!: String;
    gasto!: String;
    local!: String;
    preco!: Number;
    tipo!: ITipoGasto;

    constructor() {
        
    }
}