package com.pizzaria.controller;

import com.pizzaria.model.Permissao;
import com.pizzaria.service.PermissoesService;
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
@RequestMapping("/permissoes")
public class PermissaoController {

    private static final String CADASTRO = "permissao/CadastrarPermissao";

    @Autowired
    private PermissoesService permissoesService;

    @GetMapping("/new")
    public ModelAndView nova(Permissao permissao){
        return new ModelAndView(CADASTRO);
    }

    @PostMapping("/new")
    public ModelAndView salvar(@Valid Permissao permissao, BindingResult result,
                               RedirectAttributes attributes){

        if(result.hasErrors()){
            return nova(permissao);
        }

        permissoesService.salvar(permissao);
        attributes.addFlashAttribute("mensagem", "Permissão " + permissao.getNome() + " salva com sucesso.");
        return new ModelAndView("redirect:/permissoes/new");
    }

}
