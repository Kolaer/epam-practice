package com.epam.practice.util;

import com.epam.practice.model.Answer;

import java.util.List;

public class TestDataInput implements BayesDataInput {
    @Override
    public long getNumberOfGifts() {
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

    @Override
    public void succeed(List<Long> askedQuestionsIds, List<Answer> userAnswers, long giftId) {

    }

    void clean() {
    }

    void generate(int numberOfGifts, int numberOfQuestions) {
    }

    long pickGift() {
        return 0;
    }

    Answer getAnswer(long questionId, long giftId) {
        return Answer.IDK;
    }
}
