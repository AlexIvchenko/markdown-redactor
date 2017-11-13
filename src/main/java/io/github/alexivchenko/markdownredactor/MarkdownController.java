package io.github.alexivchenko.markdownredactor;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alex Ivchenko
 */
@Controller("/api/docs")
public class MarkdownController {
    private final MarkdownRepository repo;

    public MarkdownController(MarkdownRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    @ResponseBody
    public HttpEntity<Markdown> save(@ModelAttribute("name") final String name,
                                     @ModelAttribute("content") final String content) {
        Markdown markdown = new Markdown(name, content);
        return new HttpEntity<>(repo.save(markdown));
    }

    @GetMapping
    @ResponseBody
    public HttpEntity<Markdown> load(@RequestParam("name") final String name) {
        return new HttpEntity<>(repo.findOne(name));
    }
}
