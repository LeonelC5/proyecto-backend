package com.example.demo.repositorios;

import com.example.demo.entidades.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Long> {

    Roles findByName(String name);
}