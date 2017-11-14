package io.github.alexivchenko.markdownredactor.integration.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

/**
 * @author Alex Ivchenko
 */
public class MarkdownInfoRes extends ResourceSupport {
    private final String name;

    public MarkdownInfoRes(String name) {
        this.name = name;
    }


    @JsonProperty
    public String getName() {
        return name;
    }
}
