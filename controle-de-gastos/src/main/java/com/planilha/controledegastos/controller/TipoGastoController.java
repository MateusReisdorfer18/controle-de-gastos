package com.planilha.controledegastos.controller;

import com.planilha.controledegastos.DTO.TipoGastoRecordDTO;
import com.planilha.controledegastos.entity.TipoGasto;
import com.planilha.controledegastos.service.TipoGastoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/controle/tipogasto")
public class TipoGastoController {
    @Autowired
    private TipoGastoService service;

    @GetMapping
    public ResponseEntity<List<TipoGasto>> findAll() {
        try {
            List<TipoGasto> tipos = this.service.findAll();
            return new ResponseEntity<>(tipos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoGasto> findById(@PathVariable("id") UUID id) {
        try {
            TipoGasto tipo = this.service.findById(id);
            if(tipo == null)
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

            return new ResponseEntity<>(tipo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<TipoGasto> create(@RequestBody @Valid TipoGastoRecordDTO tipoGastoRecordDTO) {
        try {
            TipoGasto tipo = this.service.create(tipoGastoRecordDTO);
            if(tipo == null)
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

            return new ResponseEntity<>(tipo, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<TipoGasto> alter(@RequestBody @Valid TipoGastoRecordDTO tipoGastoRecordDTO, @PathVariable("id") UUID id) {
        try {
            TipoGasto tipo = this.service.alter(tipoGastoRecordDTO, id);
            if(tipo == null)
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

            return new ResponseEntity<>(tipo, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") UUID id) {
        try {
            Boolean returnExcluir = this.service.delete(id);
            if(!returnExcluir)
                return new ResponseEntity<>(returnExcluir, HttpStatus.BAD_REQUEST);

            return new ResponseEntity<>(returnExcluir, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
}
