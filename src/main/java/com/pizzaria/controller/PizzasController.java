package com.pizzaria.controller;

import com.pizzaria.model.Pizza;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pizzas")
public class PizzasController {

    private static final String CADASTRO = "pizza/CadastrarPizza";

    @RequestMapping("/new")
    public ModelAndView novo(Pizza pizza) {
        return new ModelAndView(CADASTRO);
    }

}
