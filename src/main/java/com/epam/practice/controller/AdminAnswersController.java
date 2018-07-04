package com.epam.practice.controller;

import com.epam.practice.model.Answers;
import com.epam.practice.model.AnswersKey;
import com.epam.practice.model.Gift;
import com.epam.practice.model.Question;
import com.epam.practice.model.repositories.AnswersRepository;
import com.epam.practice.model.repositories.GiftRepository;
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
public class AdminAnswersController {
    @Autowired
    private GiftRepository giftRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswersRepository answersRepository;

    private int pageSize = 15;

    @RequestMapping("/admin/answers")
    public ModelAndView adminAnswers(@RequestParam(required = false, defaultValue = "0", name = "offset") String offsetString) {
        Integer offset;

        try {
            offset = Integer.parseInt(offsetString);
        } catch (NumberFormatException ex) {
            offset = 0;
        }

        ModelAndView ret = new ModelAndView("admin/answers");

        final long count = answersRepository.count();

        ret.addObject("answersCount", count);

        long pageCount = count / pageSize;
        if (count % pageSize != 0) {
            pageCount += 1;
        }

        ret.addObject("pageCount", pageCount);
        ret.addObject("thisPage", offset);

        Page<Answers> answersPage = answersRepository.findAll(PageRequest.of(offset, pageSize));

        ret.addObject("answers", answersPage.getContent());

        return ret;
    }

    @RequestMapping(value = "/admin/answer", method = RequestMethod.GET)
    public ModelAndView adminAnswer(@RequestParam(name = "giftId") String giftIdString,
                                    @RequestParam(name = "questionId") String questionIdString) {
        Long giftId;
        Long questionId;

        try {
            giftId = Long.parseLong(giftIdString);
            questionId = Long.parseLong(questionIdString);
        } catch (NumberFormatException ex) {
            return new ModelAndView("redirect:/admin/answers");
        }

        ModelAndView ret = new ModelAndView("admin/answer");

        Optional<Gift> gift = giftRepository.findById(giftId);
        Optional<Question> question = questionRepository.findById(questionId);

        if (gift.isPresent() && question.isPresent()) {
            AnswersKey key = new AnswersKey(gift.get(), question.get());

            Optional<Answers> answers = answersRepository.findById(key);

            if (answers.isPresent()) {
                ret.addObject("answers", answers.get());

                return ret;
            }
        }

        return new ModelAndView("redirect:/admin/answers");

    }

    @RequestMapping(value = "/admin/answer", method = RequestMethod.POST)
    public String adminAnswerEdit(@RequestParam(name = "giftId") String giftIdString,
                                  @RequestParam(name = "questionId") String questionIdString,
                                  @RequestParam(name = "yes") String yesString,
                                  @RequestParam(name = "no") String noString,
                                  @RequestParam(name = "idk") String idkString) {
        Long giftId;
        Long questionId;

        Long yes;
        Long no;
        Long idk;

        try {
            giftId = Long.parseLong(giftIdString);
            questionId = Long.parseLong(questionIdString);

            yes = Long.parseLong(yesString);
            no = Long.parseLong(noString);
            idk = Long.parseLong(idkString);
        } catch (NumberFormatException ex) {
            return "redirect:/admin/answers";
        }

        Optional<Gift> gift = giftRepository.findById(giftId);
        Optional<Question> question = questionRepository.findById(questionId);

        if (gift.isPresent() && question.isPresent()) {
            AnswersKey key = new AnswersKey(gift.get(), question.get());

            Optional<Answers> answersOptional = answersRepository.findById(key);

            if (answersOptional.isPresent()) {
                Answers answers = answersOptional.get();

                answers.setAnswerYes(yes);
                answers.setAnswerNo(no);
                answers.setAnswerIdk(idk);

                answersRepository.save(answers);
            }
        }

        return "redirect:/admin/answers";
    }
}