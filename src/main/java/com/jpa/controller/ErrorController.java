package com.jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error/*")
public class ErrorController {

    @GetMapping("500")
    public void goToError500(){;}

    @GetMapping("404")
    public void goToError404(){;}

}
