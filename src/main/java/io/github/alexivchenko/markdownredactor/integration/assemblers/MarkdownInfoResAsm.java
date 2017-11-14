package io.github.alexivchenko.markdownredactor.integration.assemblers;

import io.github.alexivchenko.markdownredactor.core.model.MarkdownInfo;
import io.github.alexivchenko.markdownredactor.integration.controllers.MarkdownController;
import io.github.alexivchenko.markdownredactor.integration.resources.MarkdownInfoRes;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * @author Alex Ivchenko
 */
@Component
public class MarkdownInfoResAsm implements ResourceAssembler<MarkdownInfo, MarkdownInfoRes> {
    @Override
    public MarkdownInfoRes toResource(MarkdownInfo entity) {
        MarkdownInfoRes res = new MarkdownInfoRes(entity.getName());
        res.add(linkTo(methodOn(MarkdownController.class).getDoc(entity.getOwner().getUsername(), entity.getId()))
                .withSelfRel());
        return res;
    }
}
