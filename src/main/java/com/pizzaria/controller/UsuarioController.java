package com.pizzaria.controller;

import com.pizzaria.controller.page.PageWrapper;
import com.pizzaria.model.Usuario;
import com.pizzaria.repository.Grupos;
import com.pizzaria.repository.Usuarios;
import com.pizzaria.service.UsuariosService;
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
@RequestMapping("/usuarios")
public class UsuarioController {

    private static final String CADASTRO = "usuario/CadastrarUsuario";

    @Autowired
    private Grupos grupos;

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private Usuarios usuarios;

    @GetMapping("/new")
    public ModelAndView novo(Usuario usuario) {
        ModelAndView mv = new ModelAndView(CADASTRO);
        mv.addObject("grupos", grupos.findAll());
        return mv;
    }

    @PostMapping("/new")
    public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes, Model model) {

        if (result.hasErrors()) {
            return novo(usuario);
        }

        try {
            usuariosService.salvar(usuario);
        } catch (ObjectOptimisticLockingFailureException | StaleObjectStateException e) {
            System.out.println(e);
            model.addAttribute("mensagemErro", "Não foi possível atualizar o Usuário, talvez já tenha sido alterado por outro usuário! Atualize e tente novamente");
            return novo(usuario);
        }

        attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");
        return new ModelAndView("redirect:/usuarios/new");
    }

    @GetMapping
    public ModelAndView pesquisar(Usuario usuario, @PageableDefault(size = 9) Pageable pageable,
                                  HttpServletRequest httpServletRequest) {

        ModelAndView mv = new ModelAndView("usuario/PesquisarUsuario");

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("nome", where -> where.startsWith().ignoreCase())
                .withMatcher("cpf", where -> where.startsWith().ignoreCase());

        Page<Usuario> page = usuarios.findAll(Example.of(usuario, matcher), pageable);

        PageWrapper<Usuario> paginaWrapper =
                new PageWrapper<>(page, httpServletRequest);

        mv.addObject("pagina", paginaWrapper);
        return mv;

    }

    @GetMapping("/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Usuario usuario = usuarios.usuarioComGrupos(id);
        ModelAndView mv = novo(usuario);
        mv.addObject(usuario);
        return mv;
    }

    @DeleteMapping("/{id}")
    public @ResponseBody
    ResponseEntity<?> excluir(@PathVariable("id") Usuario usuario) {
        try {
            usuariosService.excluir(usuario);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

}
