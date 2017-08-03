package com.pizzaria.controller;

import com.pizzaria.controller.page.PageWrapper;
import com.pizzaria.model.Titulo;
import com.pizzaria.model.enumeration.Situacao;
import com.pizzaria.model.enumeration.Tipo;
import com.pizzaria.repository.CentrosDeCusto;
import com.pizzaria.repository.FormasDePagamento;
import com.pizzaria.repository.Fornecedores;
import com.pizzaria.repository.Titulos;
import com.pizzaria.service.TitulosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/titulos")
public class TituloController {

    private static final String CADASTRO = "titulo/CadastrarTitulo";

    @Autowired
    private FormasDePagamento formasDePagamento;

    @Autowired
    private CentrosDeCusto centrosDeCusto;

    @Autowired
    private Fornecedores fornecedores;

    @Autowired
    private TitulosService titulosService;

    @Autowired
    private Titulos titulos;

    @GetMapping("/new")
    public ModelAndView novo(Titulo titulo){
        ModelAndView mv = new ModelAndView(CADASTRO);
        inicializarCadastroNovoTitulo(mv);
        return mv;
    }

    @PostMapping("/new")
    public ModelAndView salvar(@Valid Titulo titulo, BindingResult result,
                               RedirectAttributes attributes){

        if (result.hasErrors()){
            return novo(titulo);
        }

        titulosService.salvar(titulo);
        attributes.addFlashAttribute("mensagem", "Título " + titulo.getId() +" salvo com sucesso");
        return new ModelAndView("redirect:/titulos/new");
    }

    private void inicializarCadastroNovoTitulo(ModelAndView mv) {
        mv.addObject("formasDePagamento", formasDePagamento.findAll());
        mv.addObject("centrosDeCusto", centrosDeCusto.findAll());
        mv.addObject("fornecedores", fornecedores.findAll());
        mv.addObject("tipos", Tipo.values());
        mv.addObject("situacoes", Situacao.values());
    }

    @GetMapping
    public ModelAndView pesquisar(Titulo titulo, Pageable pageable,
                                  HttpServletRequest httpServletRequest){

        ModelAndView mv = new ModelAndView("titulo/PesquisarTitulo");

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("descricao",  where -> where.contains().ignoreCase());

        Page<Titulo> page = titulos.findAll(Example.of(titulo, matcher), pageable);

        PageWrapper<Titulo> paginaWrapper =
                new PageWrapper<>(page, httpServletRequest);

        mv.addObject("pagina", paginaWrapper);
        return mv;

    }

}
