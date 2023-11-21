// package com.example.demo.Security;
// import com.example.demo.entidades.Roles;
// import com.example.demo.entidades.Usuario;
// import com.example.demo.repositorios.UsuarioRepository;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import java.util.Collection;
// import java.util.stream.Collectors;

// @Service
// public class CustomUserDetailsService implements UserDetailsService {

//     private UsuarioRepository usuarioRepository;

//     public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
//         this.usuarioRepository = usuarioRepository;
//     }

//     @Override
//     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//         Usuario user = usuarioRepository.findByCorreo(email);

//         if (user != null) {
//             return new org.springframework.security.core.userdetails.User(user.getCorreo(),
//                     user.getPassword(),
//                     mapRolesToAuthorities(user.getRoles()));
//         }else{
//             throw new UsernameNotFoundException("Invalid username or password.");
//         }
//     }

//     private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Roles> roles) {
//         Collection < ? extends GrantedAuthority> mapRoles = roles.stream()
//                 .map(role -> new SimpleGrantedAuthority(role.getName()))
//                 .collect(Collectors.toList());
//         return mapRoles;
//     }
// }
