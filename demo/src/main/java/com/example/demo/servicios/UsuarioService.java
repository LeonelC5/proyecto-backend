package com.example.demo.servicios;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entidades.Roles;
import com.example.demo.entidades.Usuario;
import com.example.demo.entidades.UsuarioDTO;
import com.example.demo.repositorios.RolesRepository;
import com.example.demo.repositorios.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private RolesRepository rolesRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }
    public Usuario crearUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
    public List<Usuario> obtenerTodosUsuarios() {
        return (List<Usuario>) usuarioRepository.findAll();
    }
    public Optional<Usuario> obtenerUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario actualizarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public void eliminarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }


    public UsuarioService(UsuarioRepository usuarioRepository,
                            RolesRepository roleRepository,
                            PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public Usuario crearUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNombre(usuarioDTO.getNombre() + " " + usuarioDTO.getApellido());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));

        Roles roles = rolesRepository.findByName("ROLE_ADMIN");
        if (roles == null) {
            roles = checkRoleExist();
        }
        usuario.setRoles(Arrays.asList(roles));
        return usuarioRepository.save(usuario);
    }
    public UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuarioDTO.getId());
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setNombre(usuarioDTO.getNombre());
            usuario.setCorreo(usuarioDTO.getCorreo());
            usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
            usuarioRepository.save(usuario);
            return mapToUsuarioDto(usuario);
        }
        return null; // Puedes manejar el caso de que el usuario no exista
    }

    public Optional<UsuarioDTO> obtenerUsuarioDTOPorId(Long id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        return optionalUsuario.map(this::mapToUsuarioDto);
    }
    public void saveUser(UsuarioDTO usuarioDTO) {
        Usuario Usuario = new Usuario();
        Usuario.setNombre(usuarioDTO.getNombre() + " " + usuarioDTO.getApellido());
        Usuario.setCorreo(usuarioDTO.getCorreo());
        // encrypt the password using spring security
        Usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));

        Roles roles = rolesRepository.findByName("ROLE_ADMIN");
        if(roles == null){
            roles = checkRoleExist();
        }
        Usuario.setRoles(Arrays.asList(roles));
        usuarioRepository.save(Usuario);
    }


    public UsuarioDTO findByEmail(String correo) {
        Usuario usuario = usuarioRepository.findByCorreo(correo);
        if (usuario != null) {
            return mapToUsuarioDto(usuario);
        }
        return null;
    }

    public List<UsuarioDTO> findAllUsuarios() {
        List<Usuario> Usuarios = usuarioRepository.findAll();
        return Usuarios.stream()
                .map((Usuario) -> mapToUsuarioDto(Usuario))
                .collect(Collectors.toList());
    }

    private UsuarioDTO mapToUsuarioDto(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setApellido(usuario.getApellido());
        usuarioDTO.setCorreo(usuario.getCorreo());
        return usuarioDTO;
    }

    private Roles checkRoleExist(){
        Roles roles = new Roles();
        roles.setName("ROLE_ADMIN");
        return rolesRepository.save(roles);
    }
}
