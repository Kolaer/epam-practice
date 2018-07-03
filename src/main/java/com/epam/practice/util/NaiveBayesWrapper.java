package com.epam.practice.util;

import com.epam.practice.model.Answer;
import com.epam.practice.model.Gift;
import com.epam.practice.model.Question;
import com.epam.practice.model.repositories.GiftRepository;
import com.epam.practice.model.repositories.QuestionRepository;

public class NaiveBayesWrapper {
    private NaiveBayes naiveBayes;

    private QuestionRepository questionRepository;
    private GiftRepository giftRepository;

    public NaiveBayesWrapper(NaiveBayes naiveBayes, QuestionRepository questionRepository, GiftRepository giftRepository) {
        this.naiveBayes = naiveBayes;
        this.questionRepository = questionRepository;
        this.giftRepository = giftRepository;
    }

    public Question getNextQuestion() {
        long nextQuestionId = naiveBayes.getNextQuestionId();

        return questionRepository.getOne(nextQuestionId);
    }

    public boolean canGetBest() {
        return naiveBayes.canGetBest();
    }

    public Gift getBestGift() {
        long bestGiftId = naiveBayes.getBestGiftId();

        return giftRepository.getOne(bestGiftId);
    }

    public void userAnswer(Answer answer) {
        naiveBayes.userAnswer(answer);
    }
}
