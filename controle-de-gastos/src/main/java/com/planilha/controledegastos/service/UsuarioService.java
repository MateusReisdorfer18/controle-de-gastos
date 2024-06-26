package com.planilha.controledegastos.service;

import com.planilha.controledegastos.DTO.UsuarioAutRecordDTO;
import com.planilha.controledegastos.DTO.UsuarioRecordDTO;
import com.planilha.controledegastos.entity.Usuario;
import com.planilha.controledegastos.repository.UsuarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public Usuario autenticacao(UsuarioAutRecordDTO usuarioAutRecordDTO) {
        try {
            Optional<Usuario> usuario = this.repository.findByEmailAndSenha(usuarioAutRecordDTO.email(), usuarioAutRecordDTO.senha());
            return usuario.orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Usuario> findAll() {
        try {
            return this.repository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Usuario findById(UUID id) {
        try {
            Optional<Usuario> usuario = this.repository.findById(id);
            return usuario.orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Usuario create(UsuarioRecordDTO usuarioRecordDTO) {
        try {
            Usuario usuario = new Usuario();
            BeanUtils.copyProperties(usuarioRecordDTO, usuario);
            this.repository.create(usuario.getNome(), usuario.getEmail(), usuario.getSenha(), usuario.getStatus());
            return usuario;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Usuario alter(UsuarioRecordDTO usuarioRecordDTO, UUID id) {
        try {
            Optional<Usuario> usuario = this.repository.findById(id);
            if(usuario.isEmpty())
                return null;

            BeanUtils.copyProperties(usuarioRecordDTO, usuario.get());
            this.repository.alter(usuario.get().getNome(), usuario.get().getEmail(), usuario.get().getSenha(), usuario.get().getStatus(), usuario.get().getId());
            return usuario.get();
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
