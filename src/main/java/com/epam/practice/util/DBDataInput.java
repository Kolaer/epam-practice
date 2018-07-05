package com.epam.practice.util;

import com.epam.practice.model.*;
import com.epam.practice.model.repositories.AnswersRepository;
import com.epam.practice.model.repositories.GiftRepository;
import com.epam.practice.model.repositories.QuestionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

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
