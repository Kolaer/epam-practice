package com.epam.practice.util;

import com.epam.practice.model.*;
import com.epam.practice.model.repositories.AnswersRepository;
import com.epam.practice.model.repositories.GiftRepository;
import com.epam.practice.model.repositories.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

public class DBDataInput implements BayesDataInput {
    private GiftRepository giftRepository;
    private QuestionRepository questionRepository;
    private AnswersRepository answersRepository;

    public DBDataInput(GiftRepository giftRepository, QuestionRepository questionRepository, AnswersRepository answersRepository) {
        this.giftRepository = giftRepository;
        this.questionRepository = questionRepository;
        this.answersRepository = answersRepository;
    }

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
        Gift gift = giftRepository.getOne(giftId);
        return gift.getLikes();
    }

    @Override
    public long getTotalPopularity() {
        return giftRepository.getTotalLikes();
    }

    @Override
    public long getNthGiftId(long n) {
        Page<Gift> gifts = giftRepository.findAll(PageRequest.of((int) n, 1, Sort.Direction.ASC, "gift.id"));

        Gift gift = gifts.getContent().get(0);

        return gift.getId();
    }

    @Override
    public long getNthQuestionId(long n) {
        final Page<Question> questions = questionRepository.findAll(PageRequest.of((int) n, 1, Sort.Direction.ASC, "question.id"));

        Question question = questions.getContent().get(0);

        return question.getId();
    }

    @Override
    public double getProbability(Answer answer, long giftId) {
        //TODO: write logic here
        return 0;
    }

    @Override
    public void succeed(List<Long> askedQuestionsIds, List<Answer> userAnswers, long giftId) {
        Gift bestGift = giftRepository.getOne(giftId);

        for (int i = 0; i < askedQuestionsIds.size(); i++) {
            Long questionId = askedQuestionsIds.get(i);
            Question question = questionRepository.getOne(questionId);

            Answer answer = userAnswers.get(i);

            AnswersKey answersKey = new AnswersKey(bestGift, question);

            Answers answers = answersRepository.getOne(answersKey);

            switch (answer) {
                case YES:
                    answers.setAnswerYes(answers.getAnswerYes() + 1);
                    break;
                case NO:
                    answers.setAnswerNo(answers.getAnswerNo() + 1);
                    break;
                case IDK:
                    answers.setAnswerIdk(answers.getAnswerIdk() + 1);
            }

            answersRepository.save(answers);
        }

        bestGift.setLikes(bestGift.getLikes() + 1);

        giftRepository.save(bestGift);
    }
}
