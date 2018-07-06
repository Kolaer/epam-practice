package com.epam.practice.util;

import com.epam.practice.model.*;
import com.epam.practice.model.repositories.AnswersRepository;
import com.epam.practice.model.repositories.GiftRepository;
import com.epam.practice.model.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.List;
import java.util.Optional;

@Service
public class DBDataInput implements BayesDataInput {
    @Autowired
    private GiftRepository giftRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswersRepository answersRepository;
    @Autowired
    private PlatformTransactionManager transactionManager;

    @Override
    public long getNumberOfGifts() {
        return giftRepository.count();
    }

    @Override
    public long getNumberOfQuestions() {
        return questionRepository.count();
    }

    @Override
    public long getPopularity(long giftId) {
        final Optional<Gift> optionalGift = giftRepository.findById(giftId);

        if (optionalGift.isPresent()) {
            return optionalGift.get().getLikes();
        }

        return 0;
    }

    @Override
    public long getTotalPopularity() {
        return giftRepository.getTotalLikes();
    }

    @Override
    public long getNthGiftId(long n) {
        Page<Gift> gifts = giftRepository.findAll(PageRequest.of(Math.toIntExact(n), 1));

        Gift gift = gifts.getContent().get(0);

        return gift.getId();
    }

    @Override
    public long getNthQuestionId(long n) {
        final Page<Question> questions = questionRepository.findAll(PageRequest.of((int) n, 1));

        Question question = questions.getContent().get(0);

        return question.getId();
    }

    @Override
    public double getProbability(Long questionId, Answer answer, long giftId) {
        Optional<Gift> optionalGift = giftRepository.findById(giftId);
        Optional<Question> optionalQuestion = questionRepository.findById(questionId);

        if (optionalGift.isPresent() && optionalQuestion.isPresent()) {
            AnswersKey key = new AnswersKey(optionalGift.get(), optionalQuestion.get());

            Optional<Answers> answersOptional = answersRepository.findById(key);

            if (answersOptional.isPresent()) {
                Answers answers = answersOptional.get(); // always present, because triggers

                double total = answers.getAnswerYes() + answers.getAnswerNo() + answers.getAnswerIdk();

                switch (answer) {
                    case YES:
                        return answers.getAnswerYes() / total;
                    case NO:
                        return answers.getAnswerNo() / total;
                    case IDK:
                        return answers.getAnswerIdk() / total;
                    default:
                        return 0;
                }
            } else {
                throw new IndexOutOfBoundsException();
            }
        } else {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void succeed(List<Long> askedQuestionsIds, List<Answer> userAnswers, long giftId) {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            Gift bestGift = giftRepository.findById(giftId).get();

            for (int i = 0; i < askedQuestionsIds.size(); i++) {
                Long questionId = askedQuestionsIds.get(i);

                Question question = questionRepository.findById(questionId).get();

                Answer answer = userAnswers.get(i);

                switch (answer) {
                    case YES:
                        answersRepository.incYes(bestGift, question);
                        break;
                    case NO:
                        answersRepository.incNo(bestGift, question);
                        break;
                    case IDK:
                        answersRepository.incIDK(bestGift, question);
                }
            }

            bestGift.setLikes(bestGift.getLikes() + 1);

            giftRepository.save(bestGift);

            transactionManager.commit(status);
        } catch (Exception e) {
            transactionManager.rollback(status);
            throw e;
        }
    }
}
