package com.pizzaria.controller;

import com.pizzaria.model.Promocao;
import com.pizzaria.repository.Promocoes;
import com.pizzaria.service.PromocoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/promocoes")
public class PromocaoControler {

    private static final String CADASTRO = "promocao/CadastrarPromocao";

    @Autowired
    private PromocoesService promocoesService;

    @GetMapping("/new")
    public ModelAndView novo(Promocao promocao){
        return new ModelAndView(CADASTRO);
    }

    @PostMapping("/new")
    public ModelAndView salvar(@Valid Promocao promocao, BindingResult result, RedirectAttributes attributes){

        System.out.printf(">> " + promocao.getValor());

        if (result.hasErrors()){
            return novo(promocao);
        }

        promocao = promocoesService.salvar(promocao);
        attributes.addFlashAttribute("mensagem", "Promocao " + promocao.getId() +" salva com sucesso");
        return new ModelAndView("redirect:/promocoes/new");
    }

}
