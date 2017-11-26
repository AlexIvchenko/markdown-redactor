package io.github.alexivchenko.markdownredactor.integration.domain;

import io.github.alexivchenko.markdownredactor.core.domain.MarkdownService;
import io.github.alexivchenko.markdownredactor.core.model.Markdown;
import io.github.alexivchenko.markdownredactor.core.model.MarkdownInfo;
import io.github.alexivchenko.markdownredactor.integration.assemblers.MarkdownInfoResAsm;
import io.github.alexivchenko.markdownredactor.integration.assemblers.MarkdownResAsm;
import io.github.alexivchenko.markdownredactor.integration.resources.MarkdownInfoRes;
import io.github.alexivchenko.markdownredactor.integration.resources.MarkdownRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Service
public class MarkdownIntegrationServiceImpl implements MarkdownIntegrationService {
    private final MarkdownService service;
    private final MarkdownInfoResAsm infoAsm;
    private final MarkdownResAsm resAsm;

    public MarkdownIntegrationServiceImpl(MarkdownService service, MarkdownInfoResAsm infoAsm, MarkdownResAsm resAsm) {
        this.service = service;
        this.infoAsm = infoAsm;
        this.resAsm = resAsm;
    }

    @Override
    public MarkdownRes create(String username, String docId, String content) {
        if (content == null) {
            content = "";
        }
        log.info("user: {} is creating doc: {}, size: {}", username, docId, content.length());
        Markdown markdown = service.create(username, docId, content);
        MarkdownRes res = resAsm.toResource(markdown);
        log.info("res: {}", res);
        return res;
    }

    @Override
    public MarkdownRes update(Long docId, String content) {
        log.info("updating doc: {}, size: {}", docId, content.length());
        Markdown markdown = service.update(docId, content);
        MarkdownRes res = resAsm.toResource(markdown);
        log.info("res: {}", res);
        return res;
    }

    @Override
    public Set<MarkdownInfoRes> getUserDocs(String username) {
        Set<MarkdownInfo> docs = service.getUserDocs(username);
        return docs.stream()
                .map(infoAsm::toResource)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<MarkdownInfoRes> findUserDocs(String username, String pattern) {
        Set<MarkdownInfo> docs = service.findUserDocs(username, pattern);
        return docs.stream()
                .map(infoAsm::toResource)
                .collect(Collectors.toSet());
    }

    @Override
    public MarkdownRes getDoc(Long docId) {
        Markdown doc = service.getDoc(docId);
        return resAsm.toResource(doc);
    }

    @Override
    public void delete(Long docId) {
       service.delete(docId);
    }
}
