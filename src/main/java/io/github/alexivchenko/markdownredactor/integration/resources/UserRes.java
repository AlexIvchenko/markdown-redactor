package io.github.alexivchenko.markdownredactor.integration.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

/**
 * @author Alex Ivchenko
 */
public class UserRes extends ResourceSupport {
    private final String username;

    public UserRes(String username) {
        this.username = username;
    }

    @JsonProperty
    public String getUsername() {
        return username;
    }
}
