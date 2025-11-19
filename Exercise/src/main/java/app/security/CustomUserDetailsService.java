package app.security;

import app.domain.entities.User;
import app.domain.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        // Rol en tu entidad: HR, ADMIN, DOCTOR...
        String role = user.getRole(); // por ejemplo "HR"

        // Authority que Spring espera: ROLE_HR, ROLE_ADMIN...
        GrantedAuthority authority =
                new SimpleGrantedAuthority("ROLE_" + role.toUpperCase());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(authority)
        );
    }
}
