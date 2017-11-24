package io.github.alexivchenko.markdownredactor.integration.domain;

import org.springframework.hateoas.ResourceSupport;

/**
 * @author Alex Ivchenko
 */
public interface RootIntegrationService {
    ResourceSupport availableActions();
}
