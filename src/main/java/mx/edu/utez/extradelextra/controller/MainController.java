package mx.edu.utez.extradelextra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("")
    public String index() {
        return "index";
    }

    @GetMapping("capturistas")
    public String transcribers() {
        return "transcriber_list";
    }

    @GetMapping("clientes")
    public String clients() {
        return "client_list";
    }

    @GetMapping("pedidos")
    public String orders() {
        return "order_list";
    }

    @GetMapping("productos")
    public String products() {
        return "product_list";
    }

    @GetMapping("registro")
    public String register() {
        return "register_form";
    }
}
