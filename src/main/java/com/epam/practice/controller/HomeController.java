package com.epam.practice.controller;

import com.epam.practice.model.Gift;
import com.epam.practice.model.repositories.GiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    final GiftRepository giftRepository;

    @Autowired
    public HomeController(GiftRepository giftRepository) {
        this.giftRepository = giftRepository;
    }

    @RequestMapping(value = "/hello")
    public ModelAndView hello(@RequestParam(required = false, defaultValue = "World") String name) {
        ModelAndView ret = new ModelAndView("home");
        ret.addObject("name", name);
        ret.addObject("gifts", giftRepository.findAll());
        return ret;
    }

    @RequestMapping(value = "/addGift")
    public String addGift(@RequestParam String name,
                                @RequestParam String desc,
                                @RequestParam String url) {
        Gift gift = new Gift(name, desc, url);
        giftRepository.save(gift);
        return "redirect:hello";
    }
}
