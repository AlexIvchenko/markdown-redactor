package io.github.alexivchenko.markdownredactor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Alex Ivchenko
 */
@Document
public class Markdown {
    @Id
    private String name;
    private String content;

    public Markdown() {
    }

    public Markdown(String name, String content) {
        this.name = name;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
