package com.planilha.controledegastos.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private String gasto;
    private String local;
    private Integer preco;
    private LocalDateTime dataCriacao = LocalDateTime.now();
    private LocalDateTime dataModificacao;
    private Boolean status = false;

    @ManyToOne
    private TipoGasto tipo;
}
