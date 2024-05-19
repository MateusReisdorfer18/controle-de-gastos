package com.planilha.controledegastos.repository;

import com.planilha.controledegastos.entity.TipoGasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TipoGastoRepository extends JpaRepository<TipoGasto, UUID> {
}
