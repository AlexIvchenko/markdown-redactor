package io.github.alexivchenko.markdownredactor.integration.controllers;

import io.github.alexivchenko.markdownredactor.dto.MarkdownDto;
import io.github.alexivchenko.markdownredactor.integration.domain.MarkdownIntegrationService;
import io.github.alexivchenko.markdownredactor.integration.resources.MarkdownInfoRes;
import io.github.alexivchenko.markdownredactor.integration.resources.MarkdownRes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@RestController
public class MarkdownController {

    private final MarkdownIntegrationService service;

    public MarkdownController(MarkdownIntegrationService service) {
        this.service = service;
    }

    @GetMapping("/api/users/{username}/docs")
    public Set<MarkdownInfoRes> getUserDocs(@PathVariable("username") final String username) {
        return service.getUserDocs(username);
    }

    @PostMapping(value = "/api/users/{username}/docs")
    public MarkdownRes create(@PathVariable("username") final String username, @RequestBody MarkdownDto dto) {
        log.info(dto.getName());
        return service.create(username, dto.getName(), dto.getContent());
    }

    @PostMapping("/api/users/{username}/docs/{docId}")
    public MarkdownRes update(@PathVariable("username") final String username,
                              @PathVariable("docId") final Long docId,
                              @RequestBody final String content) {
        return service.update(docId, content);
    }

    @DeleteMapping("/api/users/{username}/docs/{docId}")
    public ResponseEntity<Void> delete(@PathVariable("username") final String username,
                                       @PathVariable("docId") final Long docId) {
        service.delete(docId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/api/users/{username}/docs/{docId}")
    public MarkdownRes getDoc(@PathVariable("username") final String username,
                              @PathVariable("docId") final Long docId) {
        return service.getDoc(docId);
    }

    @GetMapping("/api/users/{username}/docs/search")
    @ResponseBody
    public Set<MarkdownInfoRes> findUserDocs(@PathVariable("username") final String username,
                                             @RequestParam("name") final String pattern) {
        return service.findUserDocs(username, pattern);
    }
}
