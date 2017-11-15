package io.github.alexivchenko.markdownredactor.integration.controllers;

import io.github.alexivchenko.markdownredactor.integration.domain.UserIntegrationService;
import io.github.alexivchenko.markdownredactor.integration.resources.UserRes;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alex Ivchenko
 */
@CrossOrigin
@RestController
public class UserController {
    private final UserIntegrationService service;

    public UserController(UserIntegrationService service) {
        this.service = service;
    }

    @PostMapping("/api/users")
    public UserRes create(@RequestParam("username") final String username,
                          @RequestParam("password") final String password) {
        return service.create(username, password);
    }
}
