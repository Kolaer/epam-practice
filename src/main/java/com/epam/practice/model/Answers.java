package com.epam.practice.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(AnswersKey.class)
public class Answers implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn
    private Gift gift;

    @Id
    @ManyToOne
    @JoinColumn
    private Question question;

    private Long answerYes;
    private Long answerNo;
    private Long answerIdk;

    public Answers() {
    }

    public Answers(Gift gift, Question question, Long answerYes, Long answerNo, Long answerIdk) {
        this.gift = gift;
        this.question = question;
        this.answerYes = answerYes;
        this.answerNo = answerNo;
        this.answerIdk = answerIdk;
    }

    @Override
    public String toString() {
        return "Answers{" +
                "gift=" + gift +
                ", question=" + question +
                ", answerYes=" + answerYes +
                ", answerNo=" + answerNo +
                ", answerIdk=" + answerIdk +
                '}';
    }

    public Gift getGift() {
        return gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Long getAnswerYes() {
        return answerYes;
    }

    public void setAnswerYes(Long answerYes) {
        this.answerYes = answerYes;
    }

    public Long getAnswerNo() {
        return answerNo;
    }

    public void setAnswerNo(Long answerNo) {
        this.answerNo = answerNo;
    }

    public Long getAnswerIdk() {
        return answerIdk;
    }

    public void setAnswerIdk(Long answerIdk) {
        this.answerIdk = answerIdk;
    }
}
