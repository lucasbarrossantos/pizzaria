package com.pizzaria.controller;

import com.pizzaria.controller.page.PageWrapper;
import com.pizzaria.model.Sabor;
import com.pizzaria.repository.Sabores;
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
@RequestMapping("/sabores")
public class SaborController {

    private static final String CADASTRO = "sabor/CadastrarSabor";

    @Autowired
    private Sabores sabores;

    @GetMapping("/new")
    public ModelAndView novo(Sabor sabor) {
        return new ModelAndView(CADASTRO);
    }

    @PostMapping("/new")
    public ModelAndView salvar(@Valid Sabor sabor, BindingResult result,
                               RedirectAttributes attributes, Model model) {

        if (result.hasErrors()) {
            return novo(sabor);
        }

        try {
            sabor = sabores.save(sabor);
        } catch (ObjectOptimisticLockingFailureException | StaleObjectStateException e) {
            System.out.println(e);
            model.addAttribute("mensagemErro", "Não foi possível atualizar a Promoção, talvez já tenha sido alterado por outro usuário! Atualize e tente novamente");
            return novo(sabor);
        } catch (RuntimeException e) {
            model.addAttribute("mensagem", e.getMessage());
            return novo(sabor);
        }


        attributes.addFlashAttribute("mensagem", "Sabor " + sabor.getId() + " salvo com sucesso");
        return new ModelAndView("redirect:/sabores/new");
    }


    @GetMapping
    public ModelAndView pesquisar(Sabor sabor, @PageableDefault(size = 9) Pageable pageable,
                                  HttpServletRequest httpServletRequest) {

        ModelAndView mv = new ModelAndView("sabor/PesquisarSabor");

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("descricao", where -> where.contains().ignoreCase());

        Page<Sabor> page = sabores.findAll(Example.of(sabor, matcher), pageable);

        PageWrapper<Sabor> paginaWrapper =
                new PageWrapper<>(page, httpServletRequest);

        mv.addObject("pagina", paginaWrapper);
        return mv;

    }

    @GetMapping("/{id}")
    public ModelAndView editar(@PathVariable("id") Sabor sabor) {
        ModelAndView mv = novo(sabor);
        mv.addObject(sabor);
        return mv;
    }

    @DeleteMapping("/{id}")
    public @ResponseBody ResponseEntity<?> excluir(@PathVariable("id") Sabor sabor) {
        try {
            sabores.delete(sabor);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

}
