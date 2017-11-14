package io.github.alexivchenko.markdownredactor.core.domain;

import io.github.alexivchenko.markdownredactor.core.model.Markdown;
import io.github.alexivchenko.markdownredactor.core.model.MarkdownContent;
import io.github.alexivchenko.markdownredactor.core.model.MarkdownInfo;
import io.github.alexivchenko.markdownredactor.core.model.User;
import io.github.alexivchenko.markdownredactor.core.repositories.mongo.MarkdownContentRepository;
import io.github.alexivchenko.markdownredactor.core.repositories.jpa.MarkdownInfoRepository;
import io.github.alexivchenko.markdownredactor.core.repositories.jpa.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Service
public class MarkdownServiceImpl implements MarkdownService {
    private final UserRepository userRepository;
    private final MarkdownInfoRepository markdownInfoRepository;
    private final MarkdownContentRepository contentRepository;

    public MarkdownServiceImpl(UserRepository userRepository, MarkdownInfoRepository markdownInfoRepository, MarkdownContentRepository contentRepository) {
        this.userRepository = userRepository;
        this.markdownInfoRepository = markdownInfoRepository;
        this.contentRepository = contentRepository;
    }

    @Override
    public Markdown create(String username, String docName, String text) {
        log.info("user {} is creating doc with name {}", username, docName);
        User user = userRepository.findByUsername(username);

        MarkdownInfo info = new MarkdownInfo(user, docName);
        info = markdownInfoRepository.save(info);
        MarkdownContent content = new MarkdownContent(info.getId(), text);
        contentRepository.save(content);
        Markdown doc = new Markdown(user, info, content);
        log.info("doc {} is created", doc);
        return doc;
    }

    @Override
    public Markdown update(Long docId, String text) {
        log.info("updating doc {}", docId);
        MarkdownContent content = contentRepository.findOne(docId);
        content.updateText(text);
        contentRepository.save(content);
        MarkdownInfo info = markdownInfoRepository.findOne(docId);
        Markdown doc = new Markdown(info.getOwner(), info, content);
        return doc;
    }

    @Override
    public Set<MarkdownInfo> getUserDocs(String username) {
        log.info("retrieving docs for {}", username);
        User user = userRepository.findByUsername(username);
        Set<MarkdownInfo> docs =  markdownInfoRepository.findByOwner(user);
        log.info("retrieved {} docs", docs.size());
        return docs;
    }

    @Override
    public Set<MarkdownInfo> findUserDocs(String username, String pattern) {
        log.info("user {} retrieving docs by {}", username, pattern);
        User user = userRepository.findByUsername(username);
        Set<MarkdownInfo> docs = markdownInfoRepository.findByOwnerAndNameLike(user, pattern);
        log.info("retrieved {} docs", docs.size());
        return docs;
    }

    @Override
    public Markdown getDoc(Long docId) {
        log.info("retrieving doc {}", docId);
        MarkdownInfo info = markdownInfoRepository.findOne(docId);
        MarkdownContent content = contentRepository.findOne(docId);
        Markdown doc = new Markdown(info.getOwner(), info, content);
        log.info("retrieved doc {}", doc);
        return doc;
    }
}
