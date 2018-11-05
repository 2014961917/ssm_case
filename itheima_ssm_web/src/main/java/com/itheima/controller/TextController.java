package com.itheima.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/text")
public class TextController {
    @RequestMapping("/text")
    public String text(){
        return "text";
    }
}
