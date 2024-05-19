package com.planilha.controledegastos.DTO;

import jakarta.validation.constraints.NotBlank;

public record UsuarioRecordDTO(@NotBlank String nome, @NotBlank String email, @NotBlank String senha) {
}
