package com.planilha.controledegastos.service;

import com.planilha.controledegastos.entity.Gasto;
import com.planilha.controledegastos.repository.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GastoService {
    @Autowired
    private GastoRepository repository;

    public List<Gasto> findAll() {
        return this.repository.findAll();
    }

    public Gasto findById(UUID id) {
        Optional<Gasto> gasto = this.repository.findById(id);
        return gasto.orElse(null);
    }

    public Gasto create(Gasto gasto) {
        gasto.setNumero(this.gerarNumero());
        return this.repository.save(gasto);
    }

    public Gasto alter(Gasto gasto, UUID id) {
        Gasto gastoEncontrado = this.findById(id);
        if(gastoEncontrado == null)
            return null;

        gastoEncontrado = gasto;
        gastoEncontrado.setDataModificacao(LocalDate.now());
        return this.repository.save(gastoEncontrado);
    }

    public Boolean updateStatus(UUID id) {
        Gasto gasto = this.findById(id);
        if(gasto == null)
            return false;

        this.repository.updateStatus(id);
        return true;
    }

    public Boolean delete(UUID id) {
        Gasto gasto = this.findById(id);
        if(gasto == null)
            return false;

        this.repository.delete(gasto);
        return true;
    }

    private Integer gerarNumero() {
        List<Gasto> gastos = this.findAll();
        if(gastos.isEmpty())
            return 1;

        return gastos.getLast().getNumero() + 1;
    }
}
