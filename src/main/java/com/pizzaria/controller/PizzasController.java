package com.pizzaria.controller;

import com.pizzaria.model.Pizza;
import com.pizzaria.model.enumeration.Tamanho;
import com.pizzaria.repository.Sabores;
import com.pizzaria.service.PizzasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/pizzas")
public class PizzasController {

    private static final String CADASTRO = "pizza/CadastrarPizza";

    @Autowired
    private Sabores sabores;

    @Autowired
    private PizzasService pizzasService;

    @RequestMapping("/new")
    public ModelAndView nova(Pizza pizza) {
        ModelAndView mv = new ModelAndView(CADASTRO);
        mv.addObject("tamanhos", Tamanho.values());
        mv.addObject("sabores", sabores.findAll());
        return mv;
    }

    @PostMapping("/new")
    public ModelAndView salvar(@Valid Pizza pizza, BindingResult result, RedirectAttributes attributes){

        if (result.hasErrors()){
            return nova(pizza);
        }

        pizza = pizzasService.salvar(pizza);
        attributes.addFlashAttribute("mensagem", "Pizza: " + pizza.getId() + " salva com sucesso!");
        return new ModelAndView("redirect:/pizzas/new");
    }

}
