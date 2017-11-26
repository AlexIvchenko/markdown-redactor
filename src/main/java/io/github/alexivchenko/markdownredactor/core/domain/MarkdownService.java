package io.github.alexivchenko.markdownredactor.core.domain;

import io.github.alexivchenko.markdownredactor.core.model.Markdown;
import io.github.alexivchenko.markdownredactor.core.model.MarkdownInfo;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface MarkdownService {
    Markdown create(String username, String docName, String content);

    Markdown update(Long docId, String content);

    Set<MarkdownInfo> getUserDocs(String username);

    Set<MarkdownInfo> findUserDocs(String username, String pattern);

    Markdown getDoc(Long  docId);

    void delete(Long docId);
}
