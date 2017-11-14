package io.github.alexivchenko.markdownredactor.core.repositories.jpa;

import io.github.alexivchenko.markdownredactor.core.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alex Ivchenko
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
