package com.epam.practice.util;

import com.epam.practice.model.Answer;

public class Tester {
    private NaiveBayes bayes;
    private TestDataInput testDataInput;

    public Tester() {
        testDataInput = new TestDataInput();
        this.bayes = new NaiveBayes(testDataInput);
    }

    public double testStrategy(int numberOfGifts, int numberOfQuestions,
                               int numberOfLearningRuns, int numberOfRuns) {
        testDataInput.clean();
        testDataInput.generate(numberOfGifts, numberOfQuestions);

        for (int i = 0; i < numberOfLearningRuns; i++) {
            long giftId = testDataInput.pickGift();

            for (int j = 0; j < 10; j++) {
                long questionId = bayes.getNextQuestionId();
                Answer answer = testDataInput.getAnswer(questionId, giftId);
                bayes.userAnswer(answer);
            }

            while (!bayes.canGetBest()) {
                long questionId = bayes.getNextQuestionId();
                Answer answer = testDataInput.getAnswer(questionId, giftId);
                bayes.userAnswer(answer);
            }

            long bestGiftId = bayes.getBestGiftId();

            if(giftId == bestGiftId) {
                bayes.succeed(bestGiftId);
            }
        }

        return 0;
    }
}
