package io.github.alexivchenko.markdownredactor.integration.domain;

import io.github.alexivchenko.markdownredactor.integration.resources.MarkdownInfoRes;
import io.github.alexivchenko.markdownredactor.integration.resources.MarkdownRes;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
public interface MarkdownIntegrationService {
    MarkdownRes create(String username, String docId, String content);

    MarkdownRes update(Long docId, String content);

    Set<MarkdownInfoRes> getUserDocs(String username);

    Set<MarkdownInfoRes> findUserDocs(String username, String pattern);

    MarkdownRes getDoc(Long docId);
}
