package com.pizzaria.controller;

import com.pizzaria.controller.page.PageWrapper;
import com.pizzaria.model.Categoria;
import com.pizzaria.model.Produto;
import com.pizzaria.repository.Categorias;
import com.pizzaria.repository.Produtos;
import com.pizzaria.repository.filter.ProdutoFilter;
import com.pizzaria.service.ProdutosService;
import com.pizzaria.service.dto.ProdutoDTO;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    private static final String CADASTRO = "produto/CadastrarProduto";

    @Autowired
    private ProdutosService produtosService;

    @Autowired
    private Categorias categorias;

    @Autowired
    private Produtos produtos;

    @RequestMapping("/new")
    public ModelAndView novo(Produto produto) {
        ModelAndView mv = new ModelAndView(CADASTRO);
        mv.addObject("categorias", categorias.findAll());
        return mv;
    }

    @PostMapping("/new")
    public ModelAndView salvar(@Valid Produto produto, BindingResult result, RedirectAttributes attributes, Model model) {

        if (result.hasErrors()) {
            return novo(produto);
        }

        try {
            produto = produtosService.salvar(produto);
        }
        catch (ObjectOptimisticLockingFailureException | StaleObjectStateException e) {
            System.out.println(e);
            model.addAttribute("mensagemErro", "Não foi possível atualizar o Produto, talvez já tenha sido alterado por outro usuário! Atualize e tente novamente");
            return novo(produto);
        }
        attributes.addFlashAttribute("mensagem", "Produto: " + produto.getDescricao() + " salvo com sucesso!");
        return new ModelAndView("redirect:/produtos/new");
    }

    @GetMapping
    public ModelAndView pesquisar(Produto produto,
                                  @PageableDefault(size = 9) Pageable pageable,
                                  HttpServletRequest httpServletRequest) {
        ModelAndView mv = new ModelAndView("produto/PesquisarProduto");

        mv.addObject("categorias", categorias.findAll());

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("descricao", where -> where.contains().ignoreCase())
                .withMatcher("categoria", where -> where.exact().exact())
                .withMatcher("sku",  where -> where.startsWith());

        Page<Produto> page = produtos.findAll(Example.of(produto, matcher), pageable);

        PageWrapper<Produto> paginaWrapper =
                new PageWrapper<>(page, httpServletRequest);

        mv.addObject("pagina", paginaWrapper);
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView editar(@PathVariable("id") Produto produto){
        ModelAndView mv = novo(produto);
        mv.addObject(produto);
        return mv;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseEntity<?> novoTipoDePagamento(@RequestBody @Valid Categoria categoria,
                                          BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldError("descricao").getDefaultMessage());
        }

        categoria = categorias.saveAndFlush(categoria);
        return ResponseEntity.ok(categoria);
    }

    @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<ProdutoDTO> pesquisar(String skuOuNome){
        return produtos.porSkuOuNome(skuOuNome);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Produto produto){
        try {
            produtosService.excluir(produto);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

}
