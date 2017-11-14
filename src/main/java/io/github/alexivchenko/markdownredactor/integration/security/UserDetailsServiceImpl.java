package io.github.alexivchenko.markdownredactor.integration.security;

import io.github.alexivchenko.markdownredactor.core.model.User;
import io.github.alexivchenko.markdownredactor.core.repositories.jpa.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Alex Ivchenko
 */
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loading user by username: {}", username);
        User user = userRepository.findByUsername(username);
        if (user == null) {
            log.info("user with username {} not found", username);
            throw new UsernameNotFoundException("username " + username + " not  found");
        }
        log.info("found user: {}", user);
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(new SimpleGrantedAuthority("ROLE_USER"))
                .build();
    }
}
