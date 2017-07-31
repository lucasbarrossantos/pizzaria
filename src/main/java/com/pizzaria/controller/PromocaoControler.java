package com.pizzaria.controller;

import com.pizzaria.model.Promocao;
import com.pizzaria.repository.Pizzas;
import com.pizzaria.service.PromocoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/promocoes")
public class PromocaoControler {

    private static final String CADASTRO = "promocao/CadastrarPromocao";

    @Autowired
    private PromocoesService promocoesService;

    @Autowired
    private Pizzas pizzas;

    @GetMapping("/new")
    public ModelAndView novo(Promocao promocao){
        ModelAndView mv = new ModelAndView(CADASTRO);
        mv.addObject("pizzas", pizzas.findAll());
        return mv;
    }

    @PostMapping("/new")
    public ModelAndView salvar(@Valid Promocao promocao, BindingResult result,
                               RedirectAttributes attributes, Model model){

        if (result.hasErrors()){
            return novo(promocao);
        }

        try {
            promocao = promocoesService.salvar(promocao);
        }catch (RuntimeException e){
            model.addAttribute("mensagem" ,e.getMessage());
            return novo(promocao);
        }
        attributes.addFlashAttribute("mensagem", "Promocao " + promocao.getId() +" salva com sucesso");
        return new ModelAndView("redirect:/promocoes/new");
    }

}
