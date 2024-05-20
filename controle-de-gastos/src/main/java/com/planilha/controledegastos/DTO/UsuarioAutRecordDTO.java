package com.planilha.controledegastos.DTO;

import jakarta.validation.constraints.NotBlank;

public record UsuarioAutRecordDTO(@NotBlank String email, @NotBlank String senha) {
}
