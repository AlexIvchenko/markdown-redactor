package io.github.alexivchenko.markdownredactor.web;

import io.github.alexivchenko.markdownredactor.integration.controllers.RootController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Alex Ivchenko
 */
@Controller
public class WebController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/editor")
    public String editor() {
        return "editor";
    }

    @RequestMapping("/signIn")
    public String signIn() {
        return "signIn";
    }

    @RequestMapping("/signUp")
    public String signUp() {
        return "signUp";
    }

    @RequestMapping("/docs")
    public String docs() {
        return "docs";
    }

    @ModelAttribute("api")
    public String api() {
        return linkTo(methodOn(RootController.class).actions()).toUri().toASCIIString();
    }
}
