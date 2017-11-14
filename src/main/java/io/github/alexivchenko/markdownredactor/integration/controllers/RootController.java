package io.github.alexivchenko.markdownredactor.integration.controllers;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Alex Ivchenko
 */
@RestController("/api")
public class RootController {
    @GetMapping
    public ResourceSupport actions() {
        ResourceSupport res = new ResourceSupport();
        res.add(linkTo(methodOn(UserController.class).create(null, null))
                .withRel("signUp"));
        return res;
    }
}
