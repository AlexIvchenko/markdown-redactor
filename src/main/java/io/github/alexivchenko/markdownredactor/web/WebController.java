package io.github.alexivchenko.markdownredactor.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Alex Ivchenko
 */
@Controller
public class WebController {
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/editor")
    public String editor() {
        return "editor";
    }
}
