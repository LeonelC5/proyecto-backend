package com.example.demo.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entidades.Usuario;
import com.example.demo.entidades.UsuarioDTO;
import com.example.demo.servicios.UsuarioService;

import java.util.List;

@Controller
public class AuthController {
    private UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    // handler method to handle home page request
    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        UsuarioDTO user = new UsuarioDTO();
        model.addAttribute("user", user);
        return "register";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") UsuarioDTO usuarioDTO,
                                BindingResult result,
                                Model model){
        UsuarioDTO existingUser = usuarioService.findByEmail(usuarioDTO.getCorreo());

        if(existingUser != null && existingUser.getCorreo() != null && !existingUser.getCorreo().isEmpty()){
            result.rejectValue("email", null,
                    "Ya existe una cuenta registrada con el mismo correo");
        }

        if(result.hasErrors()){
            model.addAttribute("user", usuarioDTO);
            return "/register";
        }

        usuarioService.saveUser(usuarioDTO);
        return "redirect:/register?success";
    }

    // handler method to handle list of users
    @GetMapping("/users")
    public String users(Model model){
        List<UsuarioDTO> users = usuarioService.findAllUsuarios();
        model.addAttribute("users", users);
        return "users";
    }

    // handler method to handle login request
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}