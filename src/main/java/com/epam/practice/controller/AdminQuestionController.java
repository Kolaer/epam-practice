package com.epam.practice.controller;

import com.epam.practice.model.Question;
import com.epam.practice.model.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class AdminQuestionController {
    @Autowired
    private QuestionRepository questionRepository;

    private int pageSize = 15;

    @RequestMapping("/admin/questions")
    public ModelAndView adminQuestions(@RequestParam(required = false, defaultValue = "0", name = "offset") String offsetString) {
        Integer offset;

        try {
            offset = Integer.parseInt(offsetString);
        } catch (NumberFormatException ex) {
            offset = 0;
        }

        ModelAndView ret = new ModelAndView("admin/questions");

        final long count = questionRepository.count();

        ret.addObject("questionCount", count);

        long pageCount = count / pageSize;
        if (count % pageSize != 0) {
            pageCount += 1;
        }

        ret.addObject("pageCount", pageCount);
        ret.addObject("thisPage", offset);

        Page<Question> questionPage = questionRepository.findAll(PageRequest.of(offset, pageSize));

        ret.addObject("questions", questionPage.getContent());

        return ret;
    }

    @RequestMapping(value = "/admin/question", method = RequestMethod.GET)
    public ModelAndView adminQuestion(@RequestParam(name = "id") String idString) {
        Long id;

        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException ex) {
            return new ModelAndView("redirect:/admin/questions");
        }

        ModelAndView ret = new ModelAndView("admin/question");

        Optional<Question> optionalQuestion = questionRepository.findById(id);

        if (optionalQuestion.isPresent()) {
            ret.addObject("question", optionalQuestion.get());

            return ret;
        } else {
            return new ModelAndView("redirect:/admin/questions");
        }
    }

    @RequestMapping(value = "/admin/question", method = RequestMethod.POST)
    public String adminQuestionEdit(@RequestParam(name = "id") String idString,
                                @RequestParam(name = "question") String questionString) {
        Long id;

        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException ex) {
            return "redirect:/admin/questions";
        }

        Optional<Question> optionalQuestion = questionRepository.findById(id);

        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();

            question.setQuestion(questionString);

            questionRepository.save(question);
        }

        return "redirect:/admin/questions";
    }

    @RequestMapping(value = "/admin/questionDelete", method = RequestMethod.POST)
    public String adminQuestionDelete(@RequestParam(name = "id") String idString) {
        Long id;

        try {
            id = Long.parseLong(idString);
        } catch (NumberFormatException ex) {
            return "redirect:/admin/questions";
        }

        questionRepository.deleteById(id);

        return "redirect:/admin/questions";
    }

    @RequestMapping(value = "/admin/questionAdd")
    public String adminQuestionAddView() {
        return "admin/questionAdd";
    }

    @RequestMapping(value = "/admin/questionAdd", method = RequestMethod.POST)
    public String adminQuestionAdd(@RequestParam(name = "question") String questionString) {
        Question question = new Question(questionString);

        questionRepository.save(question);

        return "redirect:/admin/questions";
    }
}