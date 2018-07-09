package com.epam.practice.util;

import com.epam.practice.model.Answer;

public class Tester {
    private NaiveBayes bayes;
    private TestDataInput testDataInput;

    public Tester(int numberOfGifts, int numberOfQuestions) {
        testDataInput = new TestDataInput(numberOfGifts, numberOfQuestions);
        this.bayes = new NaiveBayes(testDataInput);
    }

    public double testStrategy(int numberOfLearningRuns, int numberOfRuns) {
        for (int i = 0; i < numberOfLearningRuns; i++) {
            long giftId = testDataInput.pickGift();

            int tries = 3;
            boolean succeed = false;
            long bestGiftId = 0;

            while (tries > 0) {
                while (!bayes.canGetBest()) {
                    long questionId = bayes.getNextQuestionId();
                    Answer answer = testDataInput.getAnswer(questionId, giftId);
                    bayes.userAnswer(questionId, answer);
                }

                bestGiftId = bayes.getBestGiftId();

                if (testDataInput.normDistance(giftId, bestGiftId) < 0.2) {
                    succeed = true;
                    break;
                } else {
                    tries--;
                    long questionId = bayes.getNextQuestionId();
                    Answer answer = testDataInput.getAnswer(questionId, giftId);
                    bayes.userAnswer(questionId, answer);
                }
            }

            if(succeed) {
                bayes.succeed(bestGiftId);
            } else {
                bayes.clean();
            }
        }

        long num = 0;

        for (int i = 0; i < numberOfRuns; i++) {
            long giftId = testDataInput.pickGift();

            int tries = 3;
            boolean succeed = false;
            long bestGiftId = 0;

            while (tries > 0) {
                while (!bayes.canGetBest()) {
                    long questionId = bayes.getNextQuestionId();
                    Answer answer = testDataInput.getAnswer(questionId, giftId);
                    bayes.userAnswer(questionId, answer);
                }

                bestGiftId = bayes.getBestGiftId();

                if (testDataInput.normDistance(giftId, bestGiftId) < 0.2) {
                    num += 1;
                    succeed = true;
                    break;
                } else {
                    tries--;
                    long questionId = bayes.getNextQuestionId();
                    Answer answer = testDataInput.getAnswer(questionId, giftId);
                    bayes.userAnswer(questionId, answer);
                }
            }

            if(succeed) {
                bayes.succeed(bestGiftId);
            } else {
                bayes.clean();
            }

        }

        return (double) num / numberOfRuns;
    }

}
