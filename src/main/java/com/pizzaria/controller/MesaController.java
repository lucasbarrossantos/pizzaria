package com.pizzaria.controller;

import com.pizzaria.controller.page.PageWrapper;
import com.pizzaria.model.Mesa;
import com.pizzaria.model.enumeration.StatusMesa;
import com.pizzaria.repository.Mesas;
import com.pizzaria.service.MesasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/mesas")
public class MesaController {

    private static final String CADASTRO = "mesa/CadastrarMesa";

    @Autowired
    private MesasService mesasService;

    @Autowired
    private Mesas mesas;

    @RequestMapping("/new")
    public ModelAndView nova(Mesa mesa){
        ModelAndView mv = new ModelAndView(CADASTRO);
        return mv;
    }

    @PostMapping("/new")
    public ModelAndView salvar(@Valid Mesa mesa, BindingResult result,
                               RedirectAttributes attributes, Model model){

        if (result.hasErrors()){
            return nova(mesa);
        }

        mesa = mesasService.salvar(mesa);
        attributes.addFlashAttribute("mensagem", "Mesa " + mesa.getId() +" salva com sucesso");
        return new ModelAndView("redirect:/mesas/new");
    }

    @GetMapping
    public ModelAndView pesquisar(Mesa mesa){
        ModelAndView mv = new ModelAndView("mesa/Mesas");
        mv.addObject("mesas", mesas.findAll());
        return mv;
    }

}
