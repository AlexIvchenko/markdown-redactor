package io.github.alexivchenko.markdownredactor;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Alex Ivchenko
 */
public interface MarkdownRepository extends MongoRepository<Markdown, String> {
}
