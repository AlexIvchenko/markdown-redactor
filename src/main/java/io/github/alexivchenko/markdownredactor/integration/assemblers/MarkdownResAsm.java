package io.github.alexivchenko.markdownredactor.integration.assemblers;

import io.github.alexivchenko.markdownredactor.core.model.Markdown;
import io.github.alexivchenko.markdownredactor.integration.controllers.MarkdownController;
import io.github.alexivchenko.markdownredactor.integration.resources.MarkdownRes;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Alex Ivchenko
 */
@Component
public class MarkdownResAsm implements ResourceAssembler<Markdown, MarkdownRes> {
    @Override
    public MarkdownRes toResource(Markdown entity) {
        MarkdownRes res = new MarkdownRes(entity.getInfo().getName(), entity.getContent().getText());
        String username = entity.getOwner().getUsername();
        Long id = entity.getOwner().getId();
        res.add(linkTo(methodOn(MarkdownController.class).update(username, id, null))
                .withSelfRel());
        return res;
    }
}
