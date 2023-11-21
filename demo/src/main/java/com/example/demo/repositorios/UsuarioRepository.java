package com.example.demo.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entidades.Usuario;
import com.example.demo.entidades.UsuarioDTO;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByNombre(String nombre);
    Usuario findByCorreo(String correo);
    List<UsuarioDTO> findAll();
}
