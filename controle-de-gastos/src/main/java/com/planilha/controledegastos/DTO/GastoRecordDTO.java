package com.planilha.controledegastos.DTO;

import com.planilha.controledegastos.entity.TipoGasto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GastoRecordDTO(@NotBlank String gasto, @NotBlank String local, @NotNull Integer preco, @NotNull TipoGasto tipo) {
}
