package com.pizzaria.controller;

import com.pizzaria.controller.page.PageWrapper;
import com.pizzaria.model.Fornecedor;
import com.pizzaria.repository.Fornecedores;
import com.pizzaria.service.FornecedoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/fornecedores")
public class FornecedorController {

    private static final String CADASTRO = "fornecedor/CadastrarFornecedor";

    @Autowired
    private FornecedoresService fornecedoresService;

    @Autowired
    private Fornecedores fornecedores;

    @RequestMapping("/new")
    public ModelAndView novo(Fornecedor fornecedor) {
        return new ModelAndView(CADASTRO);
    }

    @PostMapping("/new")
    public ModelAndView salvar(@Valid Fornecedor fornecedor, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            return novo(fornecedor);
        }

        fornecedoresService.salvar(fornecedor);
        attributes.addFlashAttribute("mensagem", "Fornecedor salvo com sucesso!");
        return new ModelAndView("redirect:/fornecedores/new");
    }

    @GetMapping
    public ModelAndView pesquisar(Fornecedor fornecedor, @PageableDefault(size = 9) Pageable pageable,
                                  HttpServletRequest httpServletRequest) {

        ModelAndView mv = new ModelAndView("fornecedor/PesquisarFornecedor");

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("nomeFantasia", where -> where.startsWith().ignoreCase())
                .withMatcher("razaoSocial", where -> where.startsWith().ignoreCase());

        Page<Fornecedor> page = fornecedores.findAll(Example.of(fornecedor, matcher), pageable);

        PageWrapper<Fornecedor> paginaWrapper =
                new PageWrapper<>(page, httpServletRequest);

        mv.addObject("pagina", paginaWrapper);
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView editar(@PathVariable("id") Fornecedor fornecedor) {
        ModelAndView mv = novo(fornecedor);
        mv.addObject(fornecedor);
        return mv;
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    ResponseEntity<?> excluir(@PathVariable("id") Fornecedor fornecedor) {
        try {
            fornecedoresService.excluir(fornecedor);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

}
