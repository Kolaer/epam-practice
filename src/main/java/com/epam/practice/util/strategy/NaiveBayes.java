package com.epam.practice.util.strategy;

import com.epam.practice.model.Answer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NaiveBayes implements Strategy {
    private Set<Integer> askedQuestionsIds;
    private List<Double> giftValues;

    public NaiveBayes(int numberOfGifts) {
        askedQuestionsIds = new HashSet<>();

        giftValues = new ArrayList<>(numberOfGifts);

        final double val = 1.0 / numberOfGifts;

        for (int i = 0; i < giftValues.size(); i++) {
            giftValues.set(i, val);
        }
    }

    @Override
    public Integer getNextQuestionId() {
        return null;
    }

    @Override
    public void userAnswer(Answer answer) {

    }

    @Override
    public boolean canGetBest() {
        return false;
    }

    @Override
    public Integer getBestGiftId() {
        int best = 0;

        for (int i = 1; i < giftValues.size(); i++) {
            if (giftValues.get(i) > giftValues.get(best)) {
                best = i;
            }
        }

        return best;
    }
}
