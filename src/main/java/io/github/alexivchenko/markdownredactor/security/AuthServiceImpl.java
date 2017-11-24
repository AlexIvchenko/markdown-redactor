package io.github.alexivchenko.markdownredactor.security;

import io.github.alexivchenko.markdownredactor.core.model.MarkdownInfo;
import io.github.alexivchenko.markdownredactor.core.model.User;
import io.github.alexivchenko.markdownredactor.core.repositories.jpa.MarkdownInfoRepository;
import io.github.alexivchenko.markdownredactor.core.repositories.jpa.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Service("authService")
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final MarkdownInfoRepository markdownInfoRepository;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(UserRepository userRepository, MarkdownInfoRepository markdownInfoRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.markdownInfoRepository = markdownInfoRepository;
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

    @Override
    public User currentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(auth.getName());
    }

    @Override
    public boolean hasPermission(Authentication auth, String username) {
        log.info("checking permissions: {}, {}", username, auth);
        return auth.getName().equals(username);
    }

    @Override
    public boolean hasPermissionOnDoc(Authentication auth, Long docId) {
        log.info("checking permissions: {}, {}", docId, auth);
        String username = auth.getName();
        User user = userRepository.findByUsername(username);
        MarkdownInfo info = markdownInfoRepository.findOne(docId);
        return info.getOwner().equals(user);
    }

    @Override
    public boolean hasPermission(Authentication auth, String username, Long docId) {
        return hasPermission(auth, username) && hasPermissionOnDoc(auth, docId);
    }
}
