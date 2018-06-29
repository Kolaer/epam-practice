package com.epam.practice.util.strategy;

import com.epam.practice.model.Answer;

public interface Strategy {
    Integer getNextQuestionId();
    void userAnswer(Answer answer);

    boolean canGetBest();
    Integer getBestGiftId();
}
