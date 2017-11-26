package io.github.alexivchenko.markdownredactor.integration.domain;

import io.github.alexivchenko.markdownredactor.core.model.User;
import io.github.alexivchenko.markdownredactor.integration.controllers.MarkdownController;
import io.github.alexivchenko.markdownredactor.integration.controllers.UserController;
import io.github.alexivchenko.markdownredactor.security.AuthService;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Alex Ivchenko
 */
@Service
public class RootIntegrationServiceImpl implements RootIntegrationService {
    private final AuthService authService;

    public RootIntegrationServiceImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResourceSupport availableActions() {
        ResourceSupport links = new ResourceSupport();
        User user = authService.currentUser();
        if (user == null) {
            fillLinksForAnonymousUser(links);
        } else {
            fillLinksForAuthenticatedUser(user, links);
        }
        return links;
    }

    private void fillLinksForAuthenticatedUser(User user, ResourceSupport links) {
        links.add(linkTo(methodOn(MarkdownController.class).create(user.getUsername(), null)).withRel("createDoc"));
        links.add(linkTo(methodOn(MarkdownController.class).getUserDocs(user.getUsername())).withRel("getDocs"));
    }

    private void fillLinksForAnonymousUser(ResourceSupport links) {
        links.add(linkTo(methodOn(UserController.class).create(null))
                .withRel("signUp"));
    }
}
