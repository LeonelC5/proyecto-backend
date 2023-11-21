package com.example.demo.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import jakarta.validation.Valid;
 import org.springframework.validation.BindingResult;
 import org.springframework.web.bind.annotation.ModelAttribute;
 import org.springframework.web.bind.annotation.PostMapping;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestParam;

 import com.example.demo.entidades.UsuarioDTO;
 import com.example.demo.servicios.UsuarioService;

 @Controller
 @RequestMapping
 public class AuthController {
     private UsuarioService usuarioService;

     public AuthController(UsuarioService usuarioService) {
         this.usuarioService = usuarioService;
     }

     @GetMapping
     public ResponseEntity<List<UsuarioDTO>> obtenerTodosUsuarios() {
        return ResponseEntity.ok();
    }
     @PostMapping("/register/save")
     public ResponseEntity<String> registration(@Valid @ModelAttribute("user") UsuarioDTO usuarioDTO,
                                                 BindingResult result) {
                UsuarioDTO existingUser = usuarioService.findByEmail(usuarioDTO.getCorreo());

         if (existingUser != null && existingUser.getCorreo() != null && !existingUser.getCorreo().isEmpty()) {
             return ResponseEntity.badRequest().body("Ya existe una cuenta registrada con el mismo correo");
         }

         if (result.hasErrors()) {
             return ResponseEntity.badRequest().body("Error en los datos del formulario");
         }
         usuarioService.saveUser(usuarioDTO);
         return ResponseEntity.ok("Registro exitoso");
     }

     Endpoint para el inicio de sesión
     @PostMapping("/login")
     public ResponseEntity<String> login(@RequestParam String correo, @RequestParam String password) {
         return ResponseEntity.ok("Inicio de sesión exitoso");
     }
 }
