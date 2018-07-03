package com.epam.practice.util;

import com.epam.practice.model.Answer;

public class DBDataInput implements BayesDataInput {
    @Override
    public int getNumberOfGifts() {
        return 0;
    }

    @Override
    public long getNumberOfQuestions() {
        return 0;
    }

    @Override
    public long getPopularity(long giftId) {
        return 0;
    }

    @Override
    public long getTotalPopularity() {
        return 0;
    }

    @Override
    public long getNthGiftId(long n) {
        return 0;
    }

    @Override
    public long getNthQuestionId(long n) {
        return 0;
    }

    @Override
    public double getProbability(Answer answer, long giftId) {
        return 0;
    }
}
