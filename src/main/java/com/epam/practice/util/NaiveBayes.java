package com.epam.practice.util;

import com.epam.practice.model.Answer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class NaiveBayes {
    private BayesDataInput dataInput;

    private List<Long> askedQuestionsIds;
    private List<Answer> userAnswers;

    // uses "log" values, instead of normal,
    // because many values are close to 0,
    // and after multiplying we get 0
    private List<Double> giftValues;

    private long numberOfQuestions;

    public NaiveBayes(BayesDataInput dataInput) {
        this.dataInput = dataInput;

        this.numberOfQuestions = dataInput.getNumberOfQuestions();

        askedQuestionsIds = new ArrayList<>();
        userAnswers = new ArrayList<>();

        long numberOfGifts = dataInput.getNumberOfGifts();

        giftValues = new ArrayList<>((int) numberOfGifts);

        double totalPopularity = dataInput.getTotalPopularity();

        for (int i = 0; i < giftValues.size(); i++) {
            long giftId = dataInput.getNthGiftId(i);
            long popularity = dataInput.getPopularity(giftId);

            double val = Math.log(popularity) - Math.log(totalPopularity);

            giftValues.set(i, val);
        }
    }

    // use random, because using entropy-based method is O(n^3)
    Long getNextQuestionId() {
        long id = ThreadLocalRandom.current().nextLong(numberOfQuestions);

        return dataInput.getNthQuestionId(id);
    }

    void userAnswer(Answer answer) {
        userAnswers.add(answer);

        for (int i = 0; i < giftValues.size(); i++) {
            double val = giftValues.get(i);
            long giftId = dataInput.getNthGiftId(i);

            giftValues.set(i, val + dataInput.getProbability(answer, giftId));
        }
    }

    boolean canGetBest() {
        int len = askedQuestionsIds.size();

        return (len > 5) && (len % 5 == 0);
    }

    Long getBestGiftId() {
        long best = 0;

        for (int i = 1; i < giftValues.size(); i++) {
            if (giftValues.get(i) > giftValues.get((int) best)) {
                best = i;
            }
        }

        // make sure that this gift will not be shown in a same "game"
        giftValues.set((int) best, Double.NEGATIVE_INFINITY);

        return dataInput.getNthGiftId(best);
    }

    void succeed(long giftId) {
        dataInput.succeed(askedQuestionsIds, userAnswers, giftId);
    }
}
