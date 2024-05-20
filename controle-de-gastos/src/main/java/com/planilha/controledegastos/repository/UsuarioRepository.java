package com.planilha.controledegastos.repository;

import com.planilha.controledegastos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
    @Transactional
    @Modifying
    @Query("INSERT INTO Usuario u (u.nome, u.email, u.senha, u.status) VALUES (?1, ?2, MD5(?3), ?4)")
    public void create(String nome, String email, String senha, Boolean status);

    @Transactional
    @Modifying
    @Query("UPDATE Usuario u SET u.nome = ?1, u.email = ?2, u.senha = MD5(?3), u.status = ?4 WHERE id = ?5")
    public void alter(String nome, String email, String senha, Boolean status, UUID id);

    @Query("SELECT u FROM Usuario u WHERE u.email = ?1 AND u.senha = MD5(?2)")
    public Optional<Usuario> findByEmailAndSenha(String email, String senha);
}
