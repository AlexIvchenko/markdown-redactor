package io.github.alexivchenko.markdownredactor.integration.controllers;

import io.github.alexivchenko.markdownredactor.integration.domain.RootIntegrationService;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alex Ivchenko
 */
@RestController
public class RootController {
    private final RootIntegrationService service;

    public RootController(RootIntegrationService service) {
        this.service = service;
    }

    @GetMapping("/api")
    public ResourceSupport actions() {
        return service.availableActions();
    }
}
