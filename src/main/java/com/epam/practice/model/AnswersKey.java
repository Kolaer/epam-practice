package com.epam.practice.model;

import java.io.Serializable;

public class AnswersKey implements Serializable {
    Gift gift;
    Question question;

    public AnswersKey() {
    }

    public AnswersKey(Gift gift, Question question) {
        this.gift = gift;
        this.question = question;
    }
}
