package com.planilha.controledegastos.service;

import com.planilha.controledegastos.DTO.TipoGastoRecordDTO;
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
        try {
            return this.repository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public TipoGasto findById(UUID id) {
        try {
            Optional<TipoGasto> tipo = this.repository.findById(id);
            return tipo.orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public TipoGasto create(TipoGastoRecordDTO tipoGastoRecordDTO) {
        try {
            TipoGasto tipo = new TipoGasto();
            BeanUtils.copyProperties(tipoGastoRecordDTO, tipo);
            this.repository.save(tipo);
            return tipo;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public TipoGasto alter(TipoGastoRecordDTO tipoGastoRecordDTO, UUID id) {
        try {
            Optional<TipoGasto> tipo = this.repository.findById(id);
            if(tipo.isEmpty())
                return null;

            BeanUtils.copyProperties(tipoGastoRecordDTO, tipo.get());
            this.repository.save(tipo.get());
            return tipo.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Boolean delete(UUID id) {
        try {
            this.repository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
