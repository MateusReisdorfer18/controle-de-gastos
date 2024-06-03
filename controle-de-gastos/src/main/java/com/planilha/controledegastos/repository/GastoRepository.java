package com.planilha.controledegastos.repository;

import com.planilha.controledegastos.entity.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
@Transactional
public interface GastoRepository extends JpaRepository<Gasto, UUID> {
    @Modifying
    @Query("UPDATE Gasto g SET g.status = true WHERE g.id = ?1")
    void updateStatus(UUID id);
}
