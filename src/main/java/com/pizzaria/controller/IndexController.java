package com.pizzaria.controller;

import com.pizzaria.repository.Fornecedores;
import com.pizzaria.repository.Mesas;
import com.pizzaria.repository.Produtos;
import com.pizzaria.repository.Titulos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.time.MonthDay;
import java.util.Optional;

/**
 * Created by lucasbarros on 26/07/2017.
 */

@Controller
public class IndexController {

    private static final String VIEW = "index";

    @Autowired
    private Fornecedores fornecedores;

    @Autowired
    private Produtos produtos;

    @Autowired
    private Mesas mesas;

    @Autowired
    private Titulos titulos;

    @GetMapping("/")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView(VIEW);
        mv.addObject("fornecedores", fornecedores.findAll());
        mv.addObject("produtos", produtos.findAll());
        mv.addObject("mesas", mesas.findAll());
        mv.addObject("vendasNoMes", titulos.valorTotalNoMes(MonthDay.now().getMonthValue()).orElse(BigDecimal.ZERO));
        mv.addObject("vendasNoDia", titulos.valoresDoDia().orElse(BigDecimal.ZERO));
        return mv;
    }

}
