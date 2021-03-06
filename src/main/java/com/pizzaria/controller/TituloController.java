package com.pizzaria.controller;

import com.pizzaria.controller.page.PageWrapper;
import com.pizzaria.model.Titulo;
import com.pizzaria.model.enumeration.CentroDeCusto;
import com.pizzaria.model.enumeration.FormaDePagamento;
import com.pizzaria.model.enumeration.Situacao;
import com.pizzaria.model.enumeration.Tipo;
import com.pizzaria.repository.Fornecedores;
import com.pizzaria.repository.Titulos;
import com.pizzaria.service.TitulosService;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

@Controller
@RequestMapping("/titulos")
public class TituloController {

    private static final String CADASTRO = "titulo/CadastrarTitulo";

    @Autowired
    private Fornecedores fornecedores;

    @Autowired
    private TitulosService titulosService;

    @Autowired
    private Titulos titulos;

    @GetMapping("/new")
    public ModelAndView novo(Titulo titulo) {
        ModelAndView mv = new ModelAndView(CADASTRO);
        inicializarCadastroNovoTitulo(mv);
        return mv;
    }

    @PostMapping("/new")
    public ModelAndView salvar(@Valid Titulo titulo, BindingResult result,
                               RedirectAttributes attributes, Model model) {

        if (result.hasErrors()) {
            return novo(titulo);
        }

        try {
            titulosService.salvar(titulo);
        } catch (ObjectOptimisticLockingFailureException | StaleObjectStateException e) {
            System.out.println(e);
            model.addAttribute("mensagemErro", "Não foi possível atualizar o Título, talvez já tenha sido alterado por outro usuário! Atualize e tente novamente");
            return novo(titulo);
        }

        attributes.addFlashAttribute("mensagem", "Título " + titulo.getId() + " salvo com sucesso");
        return new ModelAndView("redirect:/titulos/new");
    }

    private void inicializarCadastroNovoTitulo(ModelAndView mv) {
        mv.addObject("formasDePagamento", FormaDePagamento.values());
        mv.addObject("centrosDeCusto", CentroDeCusto.values());
        mv.addObject("fornecedores", fornecedores.findAll());
        mv.addObject("tipos", Tipo.values());
        mv.addObject("situacoes", Situacao.values());
    }

    @GetMapping
    public ModelAndView pesquisar(Titulo titulo, @PageableDefault(size = 9) Pageable pageable,
                                  HttpServletRequest httpServletRequest) {
        ModelAndView mv = new ModelAndView("titulo/PesquisarTitulo");

        mv.addObject("formasDePagamento", FormaDePagamento.values());
        mv.addObject("centrosDeCusto", CentroDeCusto.values());
        mv.addObject("fornecedores", fornecedores.findAll());
        mv.addObject("tipos", Tipo.values());
        mv.addObject("situacoes", Situacao.values());

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("descricao", where -> where.contains().ignoreCase())
                .withMatcher("fornecedor", where -> where.exact().ignoreCase())
                .withMatcher("formaDePagamento", where -> where.exact().ignoreCase())
                .withMatcher("centroDeCusto", where -> where.exact().ignoreCase());

        Page<Titulo> page = titulos.findAll(Example.of(titulo, matcher), pageable);

        PageWrapper<Titulo> paginaWrapper =
                new PageWrapper<>(page, httpServletRequest);

        mv.addObject("pagina", paginaWrapper);
        return mv;

    }

    @GetMapping("/{id}")
    public ModelAndView editar(@PathVariable("id") Titulo titulo) {
        ModelAndView mv = novo(titulo);
        mv.addObject(titulo);
        return mv;
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    ResponseEntity<?> excluir(@PathVariable("id") Titulo titulo) {
        try {
            titulosService.excluir(titulo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

}
