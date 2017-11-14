package io.github.alexivchenko.markdownredactor.core.model;

import lombok.Getter;

/**
 * @author Alex Ivchenko
 */
@Getter
public class Markdown {
    private final User owner;
    private final MarkdownInfo info;
    private final MarkdownContent content;

    public Markdown(User owner, MarkdownInfo info, MarkdownContent content) {
        this.owner = owner;
        this.info = info;
        this.content = content;
    }
}
