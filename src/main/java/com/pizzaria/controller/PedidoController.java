package com.pizzaria.controller;

import com.pizzaria.model.Pedido;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    private static final String CADASTRO = "pedido/CadastrarPedido";

    @GetMapping("/new")
    public ModelAndView novo(Pedido pedido){
        return new ModelAndView(CADASTRO);
    }

}
