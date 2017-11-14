package io.github.alexivchenko.markdownredactor.core.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Alex Ivchenko
 */
@Document
public class MarkdownContent {
    @Id
    private final Long id;

    private String text;

    @PersistenceConstructor
    public MarkdownContent(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void updateText(String text) {
        this.text = text;
    }
}
