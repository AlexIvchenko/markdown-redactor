package io.github.alexivchenko.markdownredactor.security;

import io.github.alexivchenko.markdownredactor.core.model.User;
import org.springframework.security.core.Authentication;

/**
 * @author Alex Ivchenko
 */
public interface AuthService {
    User signUp(String username, String password);

    User currentUser();

    boolean hasPermission(Authentication auth, String username);

    boolean hasPermissionOnDoc(Authentication auth, Long docId);

    boolean hasPermission(Authentication auth, String username, Long docId);
}
