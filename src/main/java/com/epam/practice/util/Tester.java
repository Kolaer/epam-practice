package com.epam.practice.util;

import com.epam.practice.model.Answer;

import java.io.Serializable;

public class Tester implements Serializable {
    private NaiveBayes bayes;
    private TestDataInput testDataInput;

    public Tester(int numberOfGifts, int numberOfQuestions) {
        testDataInput = new TestDataInput(numberOfGifts, numberOfQuestions);
        this.bayes = new NaiveBayes(testDataInput);
    }

    public double testStrategy(int numberOfLearningRuns, int numberOfRuns) {
        for (int i = 0; i < numberOfLearningRuns; i++) {
            long giftId = testDataInput.pickGift();

            for (int j = 0; j < 10; j++) {
                long questionId = bayes.getNextQuestionId();
                Answer answer = testDataInput.getAnswer(questionId, giftId);
                bayes.userAnswer(questionId, answer);
            }

            while (!bayes.canGetBest()) {
                long questionId = bayes.getNextQuestionId();
                Answer answer = testDataInput.getAnswer(questionId, giftId);
                bayes.userAnswer(questionId, answer);
            }

            long bestGiftId = bayes.getBestGiftId();

            if(testDataInput.normDistance(giftId, bestGiftId) < 0.2) {
                bayes.succeed(bestGiftId);
            }
        }

        long num = 0;

        for (int i = 0; i < numberOfRuns; i++) {
            long giftId = testDataInput.pickGift();

            for (int j = 0; j < 10; j++) {
                long questionId = bayes.getNextQuestionId();
                Answer answer = testDataInput.getAnswer(questionId, giftId);
                bayes.userAnswer(questionId, answer);
            }

            while (!bayes.canGetBest()) {
                long questionId = bayes.getNextQuestionId();
                Answer answer = testDataInput.getAnswer(questionId, giftId);
                bayes.userAnswer(questionId, answer);
            }

            long bestGiftId = bayes.getBestGiftId();

            if(testDataInput.normDistance(giftId, bestGiftId) < 0.2) {
                num += 1;
                bayes.succeed(bestGiftId);
            }
        }

        return (double) num / numberOfRuns;
    }
}
