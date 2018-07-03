package com.epam.practice.util;

import com.epam.practice.model.Answer;

import java.util.List;

interface BayesDataInput {
    long getNumberOfGifts();

    long getNumberOfQuestions();

    long getPopularity(long giftId);

    long getTotalPopularity();

    long getNthGiftId(long n);

    long getNthQuestionId(long n);

    double getProbability(Answer answer, long giftId);

    void succeed(List<Long> askedQuestionsIds, List<Answer> userAnswers, long giftId);
}
