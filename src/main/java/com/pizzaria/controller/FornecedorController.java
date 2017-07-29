package com.pizzaria.controller;

import com.pizzaria.model.Fornecedor;
import com.pizzaria.service.FornecedoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/fornecedores")
public class FornecedorController {

    private static final String CADASTRO = "fornecedor/CadastrarFornecedor";

    @Autowired
    private FornecedoresService fornecedoresService;

    @RequestMapping("/new")
    public ModelAndView novo(Fornecedor fornecedor) {
        return new ModelAndView(CADASTRO);
    }

    @PostMapping("/new")
    public ModelAndView salvar(@Valid Fornecedor fornecedor, BindingResult result, RedirectAttributes attributes){

        if (result.hasErrors()){
            return novo(fornecedor);
        }

        fornecedoresService.salvar(fornecedor);
        attributes.addFlashAttribute("mensagem", "Fornecedor salvo com sucesso!");
        return new ModelAndView("redirect:/fornecedores/new");
    }

}
