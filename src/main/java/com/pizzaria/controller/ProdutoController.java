package com.pizzaria.controller;

import com.pizzaria.model.Categoria;
import com.pizzaria.model.Produto;
import com.pizzaria.repository.Categorias;
import com.pizzaria.service.ProdutosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    private static final String CADASTRO = "produto/CadastrarProduto";

    @Autowired
    private ProdutosService produtosService;

    @Autowired
    private Categorias categorias;

    @RequestMapping("/new")
    public ModelAndView novo(Produto produto) {
        ModelAndView mv = new ModelAndView(CADASTRO);
        mv.addObject("categorias", categorias.findAll());
        return mv;
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

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseEntity<?> novoTipoDePagamento(@RequestBody @Valid Categoria categoria,
                                          BindingResult result){

        if (result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getFieldError("descricao").getDefaultMessage());
        }

        categoria = categorias.saveAndFlush(categoria);
        return ResponseEntity.ok(categoria);
    }

}
