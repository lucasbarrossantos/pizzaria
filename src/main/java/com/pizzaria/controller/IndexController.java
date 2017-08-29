package com.pizzaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by lucasbarros on 26/07/2017.
 */

@Controller
public class IndexController {

    private static final String VIEW = "index";

    @GetMapping("/")
    public String index() {
        return VIEW;
    }

}
