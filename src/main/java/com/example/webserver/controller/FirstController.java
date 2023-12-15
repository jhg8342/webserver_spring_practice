package com.example.webserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hi")
    public String niceToMeetyou(Model model){
        model.addAttribute("username","JHG");
        return "greetings"; // templates/greetings.mustache
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model){
        model.addAttribute("nickname","구구콘");
        return "goodbye";
    }
}
