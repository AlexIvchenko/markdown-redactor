package io.github.alexivchenko.markdownredactor.integration.security;

import io.github.alexivchenko.markdownredactor.core.model.User;

/**
 * @author Alex Ivchenko
 */
public interface AuthService {
    User signUp(String username, String password);
}
