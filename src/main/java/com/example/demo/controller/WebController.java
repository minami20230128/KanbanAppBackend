package com.example.demo.controller;

import org.springframework.stereotype.Controller;

@Controller
public class WebController {
    //@RequestMapping("/{path:^(?!api).*$}")
    public String forward() {
        return "forward:/index.html";
    }
}
