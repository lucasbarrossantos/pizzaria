package com.pizzaria.controller;

import com.pizzaria.controller.page.PageWrapper;
import com.pizzaria.model.Permissao;
import com.pizzaria.repository.Permissoes;
import com.pizzaria.service.PermissoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/permissoes")
public class PermissaoController {

    private static final String CADASTRO = "permissao/CadastrarPermissao";

    @Autowired
    private PermissoesService permissoesService;

    @Autowired
    private Permissoes permissoes;

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

    @GetMapping
    public ModelAndView pesquisar(Permissao permissao, Pageable pageable,
                                  HttpServletRequest httpServletRequest){

        ModelAndView mv = new ModelAndView("permissao/PesquisarPermissao");

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("nome",  where -> where.contains().ignoreCase());

        Page<Permissao> page = permissoes.findAll(Example.of(permissao, matcher), pageable);

        PageWrapper<Permissao> paginaWrapper =
                new PageWrapper<>(page, httpServletRequest);

        mv.addObject("pagina", paginaWrapper);
        return mv;

    }

    @GetMapping("/{id}")
    public ModelAndView editar(@PathVariable("id") Permissao permissao){
        ModelAndView mv = nova(permissao);
        mv.addObject(permissao);
        return mv;
    }

}
