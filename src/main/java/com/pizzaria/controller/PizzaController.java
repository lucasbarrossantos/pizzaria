package com.pizzaria.controller;

import com.pizzaria.controller.page.PageWrapper;
import com.pizzaria.model.Pizza;
import com.pizzaria.model.enumeration.Tamanho;
import com.pizzaria.repository.Pizzas;
import com.pizzaria.repository.Sabores;
import com.pizzaria.service.PizzasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

    private static final String CADASTRO = "pizza/CadastrarPizza";

    @Autowired
    private Sabores sabores;

    @Autowired
    private PizzasService pizzasService;

    @Autowired
    private Pizzas pizzas;

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

    @GetMapping
    public ModelAndView pesquisar(Pizza pizza, Pageable pageable,
                                  HttpServletRequest httpServletRequest){

        ModelAndView mv = new ModelAndView("pizza/PesquisarPizza");
        mv.addObject("tamanhos", Tamanho.values());

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("tamanho", ExampleMatcher.GenericPropertyMatcher::exact)
                .withMatcher("descricao",  where -> where.contains().ignoreCase());

        Page<Pizza> page = pizzas.findAll(Example.of(pizza, matcher), pageable);

        PageWrapper<Pizza> paginaWrapper =
                new PageWrapper<>(page, httpServletRequest);

        mv.addObject("pagina", paginaWrapper);
        return mv;
    }

}