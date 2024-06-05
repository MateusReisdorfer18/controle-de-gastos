package com.planilha.controledegastos.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "gasto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Gasto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;

    private Integer numero;

    @NotBlank
    private String gasto;
    @NotBlank
    private String local;
    @NotNull
    private Double preco;
    private Date dataCriacao = Date.valueOf(LocalDate.now());
    private LocalDate dataModificacao;
    private Boolean status = false;

    @ManyToOne
    @NotNull
    private TipoGasto tipo;
}
