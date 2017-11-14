package io.github.alexivchenko.markdownredactor.integration.security;

import io.github.alexivchenko.markdownredactor.core.model.User;
import io.github.alexivchenko.markdownredactor.core.repositories.jpa.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public User signUp(String username, String password) {
        User found = userRepository.findByUsername(username);
        if (found != null) {
            throw new UsernameUsedException(username);
        }
        log.info("creating user with username: {}", username);
        User user = new User(username, encoder.encode(password));
        log.info("created user: {}", user);
        return userRepository.save(user);
    }
}
