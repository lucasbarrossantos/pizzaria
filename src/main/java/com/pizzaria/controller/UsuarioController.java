package com.pizzaria.controller;

import com.pizzaria.model.Usuario;
import com.pizzaria.repository.Grupos;
import com.pizzaria.service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    private static final String CADASTRO = "usuario/CadastrarUsuario";

    @Autowired
    private Grupos grupos;

    @Autowired
    private UsuariosService usuariosService;

    @GetMapping("/new")
    public ModelAndView novo(Usuario usuario){
        ModelAndView mv = new ModelAndView(CADASTRO);
        mv.addObject("grupos", grupos.findAll());
        return mv;
    }

    @PostMapping("/new")
    public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attributes){

        if (result.hasErrors()){
            return novo(usuario);
        }

        usuariosService.salvar(usuario);
        attributes.addFlashAttribute("mensagem", "Usuário salvo com sucesso!");
        return new ModelAndView("redirect:/usuarios/new");
    }

}
