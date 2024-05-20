package com.planilha.controledegastos.controller;

import com.planilha.controledegastos.DTO.GastoRecordDTO;
import com.planilha.controledegastos.DTO.TipoGastoRecordDTO;
import com.planilha.controledegastos.DTO.UsuarioAutRecordDTO;
import com.planilha.controledegastos.entity.Gasto;
import com.planilha.controledegastos.entity.TipoGasto;
import com.planilha.controledegastos.entity.Usuario;
import com.planilha.controledegastos.service.GastoService;
import com.planilha.controledegastos.service.TipoGastoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/controle/gasto")
public class GastoController {
    @Autowired
    private GastoService service;

    @GetMapping
    public ResponseEntity<List<Gasto>> findAll() {
        try {
            List<Gasto> gasto = this.service.findAll();
            return new ResponseEntity<>(gasto, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gasto> findById(@PathVariable("id") UUID id) {
        try {
            Gasto gasto = this.service.findById(id);
            if(gasto == null)
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

            return new ResponseEntity<>(gasto, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Gasto> create(@RequestBody @Valid GastoRecordDTO gastoRecordDTO) {
        try {
            Gasto gasto = this.service.create(gastoRecordDTO);
            if(gasto == null)
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

            return new ResponseEntity<>(gasto, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<Gasto> alter(@RequestBody @Valid GastoRecordDTO gastoRecordDTO, @PathVariable("id") UUID id) {
        try {
            Gasto gasto = this.service.alter(gastoRecordDTO, id);
            if(gasto == null)
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

            return new ResponseEntity<>(gasto, HttpStatus.OK);
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
