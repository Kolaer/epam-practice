package com.epam.practice.controller;

import com.epam.practice.model.Answer;
import com.epam.practice.util.NaiveBayesWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Scope("session")
public class GameController {
    @Autowired
    private NaiveBayesWrapper naiveBayesWrapper;

    @RequestMapping("/game")
    public ModelAndView game() {
        if(naiveBayesWrapper.canGetBest()) {
            ModelAndView ret = new ModelAndView("picked");

            ret.addObject("gift", naiveBayesWrapper.getBestGift());

            return ret;
        }

        ModelAndView ret = new ModelAndView("question");

        ret.addObject("question", naiveBayesWrapper.getNextQuestion());

        return ret;
    }

    @RequestMapping("/gameYes")
    public String answerYes() {
        naiveBayesWrapper.userAnswer(Answer.YES);
        return "redirect:/game";
    }

    @RequestMapping("/gameNo")
    public String answerNo() {
        naiveBayesWrapper.userAnswer(Answer.NO);
        return "redirect:/game";
    }

    @RequestMapping("/gameIDK")
    public String answerIDK() {
        naiveBayesWrapper.userAnswer(Answer.IDK);
        return "redirect:/game";
    }

    @RequestMapping("/gameSuccess")
    public String gameSuccess() {
        naiveBayesWrapper.succeed();
        return "redirect:/";
    }

    @RequestMapping("/gameFail")
    public ModelAndView gameFail() {
        ModelAndView ret = new ModelAndView("question");

        ret.addObject("question", naiveBayesWrapper.getNextQuestion());

        return ret;
    }
}
