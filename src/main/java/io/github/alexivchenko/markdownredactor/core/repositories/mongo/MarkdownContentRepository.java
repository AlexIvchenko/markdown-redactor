package io.github.alexivchenko.markdownredactor.core.repositories.mongo;

import io.github.alexivchenko.markdownredactor.core.model.MarkdownContent;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author Alex Ivchenko
 */
public interface MarkdownContentRepository extends MongoRepository<MarkdownContent, Long> {
}
