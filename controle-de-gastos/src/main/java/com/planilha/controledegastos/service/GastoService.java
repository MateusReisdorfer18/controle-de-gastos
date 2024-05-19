package com.planilha.controledegastos.service;

import com.planilha.controledegastos.DTO.GastoRecordDTO;
import com.planilha.controledegastos.DTO.TipoGastoRecordDTO;
import com.planilha.controledegastos.entity.Gasto;
import com.planilha.controledegastos.entity.TipoGasto;
import com.planilha.controledegastos.repository.GastoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GastoService {
    @Autowired
    private GastoRepository repository;

    public List<Gasto> findAll() {
        try {
            return this.repository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Gasto findById(UUID id) {
        try {
            Optional<Gasto> gasto = this.repository.findById(id);
            return gasto.orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Gasto create(GastoRecordDTO gastoRecordDTO) {
        try {
            Gasto gasto = new Gasto();
            BeanUtils.copyProperties(gastoRecordDTO, gasto);
            gasto.setNumero(this.gerarNumero());
            this.repository.save(gasto);
            return gasto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Gasto alter(GastoRecordDTO gastoRecordDTO, UUID id) {
        try {
            Optional<Gasto> gasto = this.repository.findById(id);
            if(gasto.isEmpty())
                return null;

            BeanUtils.copyProperties(gastoRecordDTO, gasto.get());
            this.repository.save(gasto.get());
            return gasto.get();
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

    private Integer gerarNumero() {
        List<Gasto> gastos = this.findAll();

        if(gastos.isEmpty())
            return 1;

        return gastos.size() + 1;
    }
}
