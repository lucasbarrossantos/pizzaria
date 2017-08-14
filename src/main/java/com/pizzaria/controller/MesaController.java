package com.pizzaria.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pedidos/mesas")
public class MesaController {

    private static final String CADASTRO = "pedido/Mesas";

    @GetMapping
    public String nova(){
        return CADASTRO;
    }

}
