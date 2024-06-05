package com.planilha.controledegastos.service;

import com.planilha.controledegastos.entity.TipoGasto;
import com.planilha.controledegastos.repository.TipoGastoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TipoGastoService {
    @Autowired
    private TipoGastoRepository repository;

    public List<TipoGasto> findAll() {
        return this.repository.findAll();
    }

    public TipoGasto findByTipo(String tipo) {
        return this.repository.findByTipo(tipo);
    }

    public TipoGasto findById(UUID id) {
        Optional<TipoGasto> tipo = this.repository.findById(id);
        return tipo.orElse(null);
    }

    public TipoGasto create(TipoGasto tipoGasto) {
       return this.repository.save(tipoGasto);
    }

    public TipoGasto alter(TipoGasto tipoGasto, UUID id) {
         TipoGasto tipoGastoEncontrado = this.findById(id);
         if(tipoGastoEncontrado == null)
             return null;

         tipoGastoEncontrado = tipoGasto;
         return this.repository.save(tipoGastoEncontrado);
    }

    public Boolean delete(UUID id) {
        TipoGasto tipoGasto = this.findById(id);
        if(tipoGasto == null)
            return false;

        this.repository.delete(tipoGasto);
        return true;
    }
}
