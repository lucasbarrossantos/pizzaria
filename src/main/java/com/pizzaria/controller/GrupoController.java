package com.pizzaria.controller;

import com.pizzaria.controller.page.PageWrapper;
import com.pizzaria.model.Grupo;
import com.pizzaria.repository.Grupos;
import com.pizzaria.repository.Permissoes;
import com.pizzaria.service.GruposService;
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
@RequestMapping("/grupos")
public class GrupoController {

    private static final String CADASTRO = "grupo/CadastrarGrupo";

    @Autowired
    private GruposService gruposService;

    @Autowired
    private Grupos grupos;

    @Autowired
    private Permissoes permissoes;

    @GetMapping("/new")
    public ModelAndView novo(Grupo grupo) {
        ModelAndView mv = new ModelAndView(CADASTRO);
        mv.addObject("permissoes", permissoes.findAll());
        return mv;
    }

    @PostMapping("/new")
    public ModelAndView salvar(@Valid Grupo grupo, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            return novo(grupo);
        }

        grupo = gruposService.salvar(grupo);
        attributes.addFlashAttribute("mensagem", "Grupo: " + grupo.getNome() + " salvo com sucesso!");
        return new ModelAndView("redirect:/grupos/new");
    }

    @GetMapping
    public ModelAndView pesquisar(Grupo grupo,
                                  @PageableDefault(size = 9, sort = {"nome"},
                                          direction = Sort.Direction.ASC) Pageable pageable,
                                  HttpServletRequest httpServletRequest) {

        ModelAndView mv = new ModelAndView("grupo/PesquisarGrupo");

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("nome", where -> where.contains().ignoreCase());

        Page<Grupo> page = grupos.findAll(Example.of(grupo, matcher), pageable);

        PageWrapper<Grupo> paginaWrapper =
                new PageWrapper<>(page, httpServletRequest);

        mv.addObject("pagina", paginaWrapper);
        return mv;

    }

    @GetMapping("/{id}")
    public ModelAndView editar(@PathVariable("id") Grupo grupo) {
        ModelAndView mv = novo(grupo);
        mv.addObject(grupo);
        return mv;
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    ResponseEntity<?> excluir(@PathVariable("id") Grupo grupo) {
        try {
            gruposService.excluir(grupo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

}
