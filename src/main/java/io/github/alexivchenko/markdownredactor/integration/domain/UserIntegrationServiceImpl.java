package io.github.alexivchenko.markdownredactor.integration.domain;

import io.github.alexivchenko.markdownredactor.core.model.User;
import io.github.alexivchenko.markdownredactor.integration.resources.UserRes;
import io.github.alexivchenko.markdownredactor.integration.security.AuthService;
import org.springframework.stereotype.Service;

/**
 * @author Alex Ivchenko
 */
@Service
public class UserIntegrationServiceImpl implements UserIntegrationService {
    private final AuthService authService;

    public UserIntegrationServiceImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public UserRes create(String username, String password) {
        User user = authService.signUp(username, password);
        return new UserRes(user.getUsername());
    }
}
