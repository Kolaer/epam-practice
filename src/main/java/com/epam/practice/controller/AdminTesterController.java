package com.epam.practice.controller;

import com.epam.practice.util.Tester;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AdminTesterController {

    @RequestMapping("/admin/tester")
    public String adminTestView() {
        return "admin/tester";
    }

    @RequestMapping("/admin/test")
    public ModelAndView adminTest(@RequestParam(name = "gifts") String giftString,
                                  @RequestParam(name = "questions") String questionString,
                                  @RequestParam(name = "learning") String learningString,
                                  @RequestParam(name = "actual") String actualString) {
        Integer gifts;
        Integer questions;
        Integer learning;
        Integer actual;

        try {
            gifts = Integer.parseInt(giftString);
            questions = Integer.parseInt(questionString);
            learning = Integer.parseInt(learningString);
            actual = Integer.parseInt(actualString);
        } catch (NumberFormatException ex) {
            return new ModelAndView("redirect:/admin/tester");
        }

        Tester tester = new Tester(gifts, questions);

        double accuracy = tester.testStrategy(learning, actual);

        ModelAndView ret = new ModelAndView("admin/testResult");

        ret.addObject("gifts", gifts);
        ret.addObject("questions", questions);
        ret.addObject("learning", learning);
        ret.addObject("actual", actual);
        ret.addObject("accuracy", accuracy);

        return ret;
    }
}
