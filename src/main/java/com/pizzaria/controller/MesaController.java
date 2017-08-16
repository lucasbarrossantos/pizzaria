package com.pizzaria.controller;

import com.pizzaria.model.Mesa;
import com.pizzaria.model.Pedido;
import com.pizzaria.model.enumeration.StatusMesa;
import com.pizzaria.repository.Mesas;
import com.pizzaria.service.MesasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.UUID;

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
        if (StringUtils.isEmpty(mesa.getNumero())){
            mesa.setNumero(String.valueOf(mesas.findAll().size() + 1));
            mv.addObject(mesa);
        }
        mv.addObject("status", StatusMesa.values());
        return mv;
    }

    @GetMapping("pedidos/{id}")
    public ModelAndView novo(@PathVariable("id") Long id, RedirectAttributes attributes) {
        ModelAndView mv = new ModelAndView("redirect:/pedidos/new");
        Mesa mesa = mesas.findOne(id);
        Pedido pedido = new Pedido();
        pedido.setMesa(mesa);
        pedido.setUuid(UUID.randomUUID().toString());
        attributes.addFlashAttribute(pedido);
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

    @GetMapping("/{id}")
    public ModelAndView editar(@PathVariable("id") Mesa mesa){
        ModelAndView mv = new ModelAndView(CADASTRO);
        mv.addObject("status", StatusMesa.values());
        mv.addObject(mesa);
        return mv;
    }

}
