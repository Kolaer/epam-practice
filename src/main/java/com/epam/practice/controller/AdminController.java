package com.epam.practice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

    @RequestMapping("/admin")
    public String admin() {
        return "redirect:/admin/gifts";
    }

    @RequestMapping("/admin/answers")
    public String adminAnswers(ModelMap modelMap) {
        return "admin/answers";
    }

    @RequestMapping("/admin/tester")
    public String adminTester(ModelMap modelMap) {
        return "admin/tester";
    }
}
