package com.pizzaria.controller;

import com.pizzaria.controller.page.PageWrapper;
import com.pizzaria.model.Mesa;
import com.pizzaria.model.Pedido;
import com.pizzaria.model.enumeration.StatusMesa;
import com.pizzaria.repository.Mesas;
import com.pizzaria.service.MesasService;
import org.hibernate.StaleObjectStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
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

        try {
            mesa = mesasService.salvar(mesa);
        }catch (ObjectOptimisticLockingFailureException | StaleObjectStateException e){
            System.out.println(e);
            model.addAttribute("mensagemErro", "Não foi possível atualizar a Mesa, talvez já tenha sido alterado por outro usuário! Atualize e tente novamente");
            return nova(mesa);
        }
        attributes.addFlashAttribute("mensagem", "Mesa " + mesa.getId() +" salva com sucesso");
        return new ModelAndView("redirect:/mesas/new");
    }

    @GetMapping
    public ModelAndView pesquisar(Mesa mesa, @PageableDefault(size = 9) Pageable pageable,
                                  HttpServletRequest httpServletRequest){
        ModelAndView mv = new ModelAndView("mesa/Mesas");
        mv.addObject("statusMesa", StatusMesa.values());

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("status", where -> where.exact());

        Page<Mesa> page = mesas.findAll(Example.of(mesa, matcher), pageable);

        PageWrapper<Mesa> paginaWrapper =
                new PageWrapper<>(page, httpServletRequest);

        mv.addObject("pagina", paginaWrapper);
        return mv;
    }

    @GetMapping("/{id}")
    public ModelAndView editar(@PathVariable("id") Mesa mesa){
        ModelAndView mv = new ModelAndView(CADASTRO);
        mv.addObject("status", StatusMesa.values());
        mv.addObject(mesa);
        return mv;
    }

    @GetMapping("/fechar/mesa/{id}")
    public ModelAndView fecharPedido(@PathVariable("id") Mesa mesa, RedirectAttributes attributes){
        ModelAndView mv = new ModelAndView("redirect:/mesas");
        mesasService.fecharMesa(mesa);
        attributes.addFlashAttribute("mensagem", "Mesa " + mesa.getId() +" fechada com sucesso");
        return mv;
    }

}
