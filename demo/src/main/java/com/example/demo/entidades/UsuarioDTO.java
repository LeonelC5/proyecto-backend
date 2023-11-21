package com.example.demo.entidades;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;


public class UsuarioDTO
{
    private Long id;
    @NotEmpty
    private String nombre;
    @NotEmpty
    private String apellido;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String correo;
    @NotEmpty(message = "Password should not be empty")
    private String password;
    public UsuarioDTO() {
    }
    public UsuarioDTO(Long id, @NotEmpty String nombre, @NotEmpty String apellido,
            @NotEmpty(message = "Email should not be empty") @Email String correo,
            @NotEmpty(message = "Password should not be empty") String password) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.password = password;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    
}