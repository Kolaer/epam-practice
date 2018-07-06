package com.epam.practice.util;

import com.epam.practice.model.Answer;
import com.epam.practice.model.Gift;
import com.epam.practice.model.Question;
import com.epam.practice.model.repositories.GiftRepository;
import com.epam.practice.model.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class NaiveBayesWrapper {
    private NaiveBayes naiveBayes;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private GiftRepository giftRepository;

    private Gift bestGift;
    private Long questionId;

    @Autowired
    public NaiveBayesWrapper(DBDataInput dbDataInput) {
        this.naiveBayes = new NaiveBayes(dbDataInput);
    }

    public Question getNextQuestion() {
        long nextQuestionId = naiveBayes.getNextQuestionId();

        this.questionId = nextQuestionId;

        return questionRepository.findById(nextQuestionId).get();
    }

    public boolean canGetBest() {
        return naiveBayes.canGetBest();
    }

    public Gift getBestGift() {
        long bestGiftId = naiveBayes.getBestGiftId();

        bestGift = giftRepository.findById(bestGiftId).get();

        return bestGift;
    }

    public void userAnswer(Answer answer) {
        naiveBayes.userAnswer(questionId, answer);
    }

    public void succeed() {
        naiveBayes.succeed(bestGift.getId());
    }
}
