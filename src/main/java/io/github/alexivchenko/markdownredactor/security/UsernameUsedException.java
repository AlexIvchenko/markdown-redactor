package io.github.alexivchenko.markdownredactor.security;

/**
 * @author Alex Ivchenko
 */
public class UsernameUsedException extends RuntimeException {
    public UsernameUsedException(String username) {
        super("username " + username + " already existed");
    }
}
