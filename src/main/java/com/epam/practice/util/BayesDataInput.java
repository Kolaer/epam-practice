package com.epam.practice.util;

import com.epam.practice.model.Answer;

interface BayesDataInput {
    int getNumberOfGifts();

    long getNumberOfQuestions();

    long getPopularity(long giftId);

    long getTotalPopularity();

    long getNthGiftId(long n);

    long getNthQuestionId(long n);

    double getProbability(Answer answer, long giftId);
}
