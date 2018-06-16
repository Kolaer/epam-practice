package com.epam.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @RequestMapping(value = "/hello")
    public ModelAndView hello(@RequestParam(required = false, defaultValue = "World") String name) {
        ModelAndView ret = new ModelAndView("home");
        ret.addObject("name", name);
        return ret;
    }
}
