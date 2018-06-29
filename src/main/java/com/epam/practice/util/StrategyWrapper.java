package com.epam.practice.util;

import com.epam.practice.model.Answer;
import com.epam.practice.model.Gift;
import com.epam.practice.model.Question;
import com.epam.practice.model.repositories.GiftRepository;
import com.epam.practice.model.repositories.QuestionRepository;
import com.epam.practice.util.strategy.Strategy;

public class StrategyWrapper {
    private Strategy strategy;

    private QuestionRepository questionRepository;
    private GiftRepository giftRepository;

    public StrategyWrapper(Strategy strategy, QuestionRepository questionRepository, GiftRepository giftRepository) {
        this.strategy = strategy;
        this.questionRepository = questionRepository;
        this.giftRepository = giftRepository;
    }

    public Question getNextQuestion() {
        final Integer nextQuestionId = strategy.getNextQuestionId();

        return questionRepository.getOne(nextQuestionId.longValue());
    }

    public boolean canGetBest() {
        return strategy.canGetBest();
    }

    public Gift getBestGift() {
        final Integer bestGiftId = strategy.getBestGiftId();

        return giftRepository.getOne(bestGiftId.longValue());
    }

    public void userAnswer(Answer answer) {
        strategy.userAnswer(answer);
    }
}
