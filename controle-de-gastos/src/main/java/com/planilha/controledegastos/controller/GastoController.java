package com.planilha.controledegastos.controller;

import com.planilha.controledegastos.entity.Gasto;
import com.planilha.controledegastos.service.GastoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/gasto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class GastoController {
    @Autowired
    private GastoService service;

    @GetMapping
    public ResponseEntity<List<Gasto>> findAll() {
        List<Gasto> gasto = this.service.findAll();
        return ResponseEntity.ok(gasto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gasto> findById(@PathVariable("id") UUID id) {
        Gasto gasto = this.service.findById(id);
        if(gasto == null)
            ResponseEntity.notFound().build();

        return ResponseEntity.ok(gasto);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Gasto> create(@RequestBody @Valid Gasto gasto) {
        Gasto gastoCriado = this.service.create(gasto);
        if(gastoCriado == null)
            return ResponseEntity.badRequest().build();

        return new ResponseEntity<>(gastoCriado, HttpStatus.CREATED);
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<Gasto> alter(@RequestBody @Valid Gasto gasto, @PathVariable("id") UUID id) {
        Gasto gastoAlterado = this.service.alter(gasto, id);
        if(gastoAlterado == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(gastoAlterado);
    }

    @PatchMapping("/alterar/status/{id}")
    public ResponseEntity<Boolean> updateStatus(@PathVariable("id") UUID id) {
        Boolean returnUpdateStatus = this.service.updateStatus(id);
        if(!returnUpdateStatus)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(true);
    }

    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") UUID id) {
        Boolean returnExcluir = this.service.delete(id);
        if(!returnExcluir)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(true);
    }
}
