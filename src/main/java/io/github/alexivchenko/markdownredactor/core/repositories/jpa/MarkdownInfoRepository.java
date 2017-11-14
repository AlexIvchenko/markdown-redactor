package io.github.alexivchenko.markdownredactor.core.repositories.jpa;

import io.github.alexivchenko.markdownredactor.core.model.MarkdownInfo;
import io.github.alexivchenko.markdownredactor.core.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@Repository
public interface MarkdownInfoRepository extends CrudRepository<MarkdownInfo, Long> {
    Set<MarkdownInfo> findByOwner(User owner);

    Set<MarkdownInfo> findByOwnerAndNameLike(User owner, String pattern);
}
