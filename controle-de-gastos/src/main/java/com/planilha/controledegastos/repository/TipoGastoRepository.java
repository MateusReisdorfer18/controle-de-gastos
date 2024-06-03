package com.planilha.controledegastos.repository;

import com.planilha.controledegastos.entity.TipoGasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TipoGastoRepository extends JpaRepository<TipoGasto, UUID> {
    TipoGasto findByTipo(String tipo);
}
