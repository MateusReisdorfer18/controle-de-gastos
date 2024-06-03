import { ITipoGasto } from "../tipoGasto/ITipoGasto"

export interface IGasto {
    id: String,
    numero: Number,
    gasto: String,
    local: String,
    preco: Number,
    dataCriacao: Date,
    diaCriacao: Number,
    mesCriacao: Number,
    anoCriacao: Number,
    dataModificao: Date,
    tipo: ITipoGasto,
    status: Boolean
}