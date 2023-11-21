package com.example.demo.entidades;

import jakarta.persistence.*;


import java.util.List;

@Entity
public class Roles
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String name;

    
    @ManyToMany(mappedBy="roles")
    private List<Usuario> usuarios;
    public Roles() {
    }

    public Roles(Long id, String name, List<Usuario> usuarios) {
        this.id = id;
        this.name = name;
        this.usuarios = usuarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Usuario> getUsers() {
        return usuarios;
    }

    public void setUsers(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

}