package com.planilha.controledegastos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tipo_gasto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoGasto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @NotBlank
    private String tipo;
}
