package com.epam.practice.util;

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
        return 0;
    }
}
