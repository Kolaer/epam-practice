package com.epam.practice.controller;

import com.epam.practice.model.repositories.AnswersRepository;
import com.epam.practice.model.repositories.GiftRepository;
import com.epam.practice.model.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
    private final GiftRepository giftRepository;
    private final QuestionRepository questionRepository;
    private final AnswersRepository answersRepository;

    @Autowired
    public AdminController(GiftRepository giftRepository, QuestionRepository questionRepository, AnswersRepository answersRepository) {
        this.giftRepository = giftRepository;
        this.questionRepository = questionRepository;
        this.answersRepository = answersRepository;
    }

    @RequestMapping("/admin")
    public String admin(ModelMap modelMap) {
        return "admin";
    }
}
