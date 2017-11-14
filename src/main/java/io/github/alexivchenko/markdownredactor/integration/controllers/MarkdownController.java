package io.github.alexivchenko.markdownredactor.integration.controllers;

import io.github.alexivchenko.markdownredactor.integration.domain.MarkdownIntegrationService;
import io.github.alexivchenko.markdownredactor.integration.resources.MarkdownInfoRes;
import io.github.alexivchenko.markdownredactor.integration.resources.MarkdownRes;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * @author Alex Ivchenko
 */
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

    @PostMapping("/api/users/{username}/docs")
    public MarkdownRes create(@PathVariable("username") final String username,
                                          @ModelAttribute("name") final String name,
                                          @ModelAttribute("content") final String content) {
        return service.create(username, name, content);
    }

    @PostMapping("/api/users/{username}/docs/{docId}")
    public MarkdownRes update(@PathVariable("username") final String username,
                              @PathVariable("docId") final Long docId,
                              @ModelAttribute("content") final String content) {
        return service.update(docId, content);
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
