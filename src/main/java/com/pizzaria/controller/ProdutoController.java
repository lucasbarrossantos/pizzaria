package com.pizzaria.controller;

import com.pizzaria.model.Produto;
import com.pizzaria.service.ProdutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    private static final String CADASTRO = "produto/CadastrarProduto";

    @Autowired
    private ProdutosService produtosService;

    @RequestMapping("/new")
    public ModelAndView novo(Produto produto) {
        return new ModelAndView(CADASTRO);
    }

    @PostMapping("/new")
    public ModelAndView salvar(@Valid Produto produto, BindingResult result, RedirectAttributes attributes){

        if (result.hasErrors()){
            return novo(produto);
        }

        produto = produtosService.salvar(produto);
        attributes.addFlashAttribute("mensagem", "Produto: " + produto.getDescricao() + " salvo com sucesso!");
        return new ModelAndView("redirect:/produtos/new");
    }

}
