package io.github.alexivchenko.markdownredactor.integration.domain;

import io.github.alexivchenko.markdownredactor.integration.resources.UserRes;

/**
 * @author Alex Ivchenko
 */
public interface UserIntegrationService {
    UserRes create(String username, String password);
}
