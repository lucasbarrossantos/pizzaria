package com.pizzaria.controller;

import com.pizzaria.model.Grupo;
import com.pizzaria.service.GruposService;
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
@RequestMapping("/grupos")
public class GrupoController {

    private static final String CADASTRO = "grupo/CadastrarGrupo";

    @Autowired
    private GruposService gruposService;

    @GetMapping("/new")
    public ModelAndView novo(Grupo grupo){
        return new ModelAndView(CADASTRO);
    }

    @PostMapping("/new")
    public ModelAndView salvar(@Valid Grupo grupo, BindingResult result, RedirectAttributes attributes){

        if (result.hasErrors()){
            return novo(grupo);
        }

        grupo = gruposService.salvar(grupo);
        attributes.addFlashAttribute("mensagem", "Grupo: " + grupo.getNome() + " salvo com sucesso!");
        return new ModelAndView("redirect:/grupos/new");
    }

}
