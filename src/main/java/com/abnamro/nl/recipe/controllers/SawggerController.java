package com.abnamro.nl.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SawggerController {
    @RequestMapping("/")
    public String swaggerUI(){
        return "redirect:/swagger-ui/index.html?url=/v3/api-docs";
    }
}
