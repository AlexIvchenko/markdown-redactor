package io.github.alexivchenko.markdownredactor.integration.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import org.springframework.hateoas.ResourceSupport;

/**
 * @author Alex Ivchenko
 */
@ToString(of = "name")
public class MarkdownRes extends ResourceSupport {
    private final String name;
    private final String content;

    public MarkdownRes(String name, String content) {
        this.name = name;
        this.content = content;
    }

    @JsonProperty
    public String getName() {
        return name;
    }

    @JsonProperty
    public String getContent() {
        return content;
    }
}
