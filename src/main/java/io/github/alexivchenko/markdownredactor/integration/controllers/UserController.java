package io.github.alexivchenko.markdownredactor.integration.controllers;

import io.github.alexivchenko.markdownredactor.dto.UserDto;
import io.github.alexivchenko.markdownredactor.integration.domain.UserIntegrationService;
import io.github.alexivchenko.markdownredactor.integration.resources.UserRes;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alex Ivchenko
 */
@RestController
public class UserController {
    private final UserIntegrationService service;

    public UserController(UserIntegrationService service) {
        this.service = service;
    }

    @PostMapping("/api/users")
    public UserRes create(@RequestBody final UserDto dto) {
        return service.create(dto.getUsername(), dto.getPassword());
    }
}
