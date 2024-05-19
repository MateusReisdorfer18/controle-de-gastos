package com.planilha.controledegastos.controller;

import com.planilha.controledegastos.DTO.UsuarioRecordDTO;
import com.planilha.controledegastos.entity.Usuario;
import com.planilha.controledegastos.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        try {
            List<Usuario> usuarios = this.service.findAll();
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> findById(@PathVariable("id")UUID id) {
        try {
            Usuario usuario = this.service.findById(id);
            if(usuario == null)
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Usuario> create(@RequestBody @Valid UsuarioRecordDTO usuarioRecordDTO) {
        try {
            Usuario usuario = this.service.create(usuarioRecordDTO);
            if(usuario == null)
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

            return new ResponseEntity<>(usuario, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<Usuario> alter(@RequestBody @Valid UsuarioRecordDTO usuarioRecordDTO, @PathVariable("id") UUID id) {
        try {
            Usuario usuario = this.service.alter(usuarioRecordDTO, id);
            if(usuario == null)
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

            return new ResponseEntity<>(usuario, HttpStatus.OK);
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
                return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);

            return new ResponseEntity<>(returnExcluir, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }
}
