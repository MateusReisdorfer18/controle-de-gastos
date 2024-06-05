package com.planilha.controledegastos.controller;

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
@RequestMapping("/tipo-gasto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TipoGastoController {
    @Autowired
    private TipoGastoService service;

    @GetMapping
    public ResponseEntity<List<TipoGasto>> findAll() {
        List<TipoGasto> tipos = this.service.findAll();
        return ResponseEntity.ok(tipos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoGasto> findById(@PathVariable("id") UUID id) {
        TipoGasto tipo = this.service.findById(id);
        if (tipo == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(tipo);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<TipoGasto> findByTipo(@PathVariable("tipo") String tipo) {
        TipoGasto tipoGasto = this.service.findByTipo(tipo);
        if(tipoGasto == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(tipoGasto);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<TipoGasto> create(@RequestBody @Valid TipoGasto tipoGasto) {
        TipoGasto tipo = this.service.create(tipoGasto);
        if(tipo == null)
            return ResponseEntity.badRequest().build();

        return new ResponseEntity<>(tipo, HttpStatus.CREATED);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<TipoGasto> alter(@RequestBody @Valid TipoGasto tipoGasto, @PathVariable("id") UUID id) {
        TipoGasto tipo = this.service.alter(tipoGasto, id);
        if(tipo == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(tipo);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") UUID id) {
        Boolean returnExcluir = this.service.delete(id);
        if(!returnExcluir)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(true);
    }
}
