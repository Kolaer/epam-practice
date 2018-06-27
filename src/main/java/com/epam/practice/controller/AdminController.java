package com.epam.practice.controller;

import com.epam.practice.model.repositories.AnswersRepository;
import com.epam.practice.model.repositories.GiftRepository;
import com.epam.practice.model.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AdminController {
    private final GiftRepository giftRepository;
    private final QuestionRepository questionRepository;
    private final AnswersRepository answersRepository;

    private String PASS_HASH = "f5bb0c8de146c67b44babbf4e6584cc0"; // Hash of 123123123

    @Autowired
    public AdminController(GiftRepository giftRepository, QuestionRepository questionRepository, AnswersRepository answersRepository) {
        this.giftRepository = giftRepository;
        this.questionRepository = questionRepository;
        this.answersRepository = answersRepository;
    }

    @RequestMapping("/admin")
    public String admin(HttpServletRequest request, ModelMap modelMap) {
        HttpSession session = request.getSession();
        Object password_hash = session.getAttribute("password_hash");

        if (null != password_hash) {
            if (password_hash.equals(PASS_HASH)) {
                //TODO: add data to model
                return "admin";
            } else {
                session.removeAttribute("password_hash");
                return "redirect:login";
            }
        }

        return "redirect:login";
    }

    @RequestMapping("logout")
    public String adminLogout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("password_hash");

        return "redirect:/";
    }
}
