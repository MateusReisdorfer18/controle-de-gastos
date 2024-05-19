package com.planilha.controledegastos.DTO;

import jakarta.validation.constraints.NotBlank;

public record TipoGastoRecordDTO(@NotBlank String tipo) {
}
