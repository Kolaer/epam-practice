package com.epam.practice.util;

import com.epam.practice.model.Answer;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class TestDataInput implements BayesDataInput, Serializable {
    private class Point2D {
        private double x;
        private double y;

        public Point2D(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point2D point2D = (Point2D) o;
            return Double.compare(point2D.x, x) == 0 &&
                    Double.compare(point2D.y, y) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Point2D{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    private long[] likes;
    private Point2D[] giftsPoints;
    private Point2D[][] questionLines;
    private long[][] questionAnswers;

    private long numberOfGifts;
    private long numberOfQuestions;

    @Override
    public long getNumberOfGifts() {
        return numberOfGifts;
    }

    @Override
    public long getNumberOfQuestions() {
        return numberOfQuestions;
    }

    @Override
    public long getPopularity(long giftId) {
        return likes[Math.toIntExact(giftId)];
    }

    @Override
    public long getTotalPopularity() {
        long sum = 0;

        for (long like : likes) {
            sum += like;
        }

        return sum;
    }

    @Override
    public long getNthGiftId(long n) {
        return n;
    }

    @Override
    public long getNthQuestionId(long n) {
        return n;
    }

    @Override
    public double getProbability(Long questionId, Answer answer, long giftId) {
        int id = Math.toIntExact(giftId * numberOfQuestions + questionId);

        double total = questionAnswers[id][0] + questionAnswers[id][1] + questionAnswers[id][2];

        switch (answer) {
            case YES:
                return questionAnswers[id][0] / total;
            case NO:
                return questionAnswers[id][1] / total;
            case IDK:
                return questionAnswers[id][2] / total;
        }

        return 0;
    }

    @Override
    public void succeed(List<Long> askedQuestionsIds, List<Answer> userAnswers, long giftId) {
        for (int i = 0; i < askedQuestionsIds.size(); i++) {
            long id = giftId * numberOfQuestions + askedQuestionsIds.get(i);
            Answer answer = userAnswers.get(i);

            int answerId = 0;
            switch (answer) {
                case NO:
                    answerId = 1;
                    break;
                case IDK:
                    answerId = 2;
            }

            questionAnswers[Math.toIntExact(id)][answerId] += 1;
        }

        likes[Math.toIntExact(giftId)] += 1;
    }

    TestDataInput(int numberOfGifts, int numberOfQuestions) {
        this.numberOfGifts = numberOfGifts;
        this.numberOfQuestions = numberOfQuestions;

        likes = new long[numberOfGifts];

        for (int i = 0; i < likes.length; i++) {
            likes[i] = 1;
        }

        giftsPoints = new Point2D[numberOfGifts];

        for (int i = 0; i < giftsPoints.length; i++) {
            double x = ThreadLocalRandom.current().nextDouble();
            double y = ThreadLocalRandom.current().nextDouble();

            giftsPoints[i] = new Point2D(x, y);
        }

        questionLines = new Point2D[numberOfQuestions][2];

        for (int i = 0; i < questionLines.length; i++) {
            double x = ThreadLocalRandom.current().nextDouble();
            double y = ThreadLocalRandom.current().nextDouble();

            questionLines[i][0] = new Point2D(x, y);

            x = ThreadLocalRandom.current().nextDouble();
            y = ThreadLocalRandom.current().nextDouble();

            questionLines[i][1] = new Point2D(x, y);
        }

        questionAnswers = new long[numberOfQuestions * numberOfGifts][3];

        for (int i = 0; i < questionAnswers.length; i++) {
            for (int j = 0; j < 3; j++) {
                questionAnswers[i][j] = 1;
            }
        }
    }

    long pickGift() {
        return ThreadLocalRandom.current().nextLong(numberOfGifts);
    }

    Answer getAnswer(long questionId, long giftId) {
        double p = ThreadLocalRandom.current().nextDouble();

        if (p < 0.05) {
            return Answer.IDK;
        }

        final int question = Math.toIntExact(questionId);

        double x1 = questionLines[question][0].getX();
        double y1 = questionLines[question][0].getY();

        double x2 = questionLines[question][1].getX();
        double y2 = questionLines[question][1].getY();

        final int gift = Math.toIntExact(giftId);

        double x3 = giftsPoints[gift].getX();
        double y3 = giftsPoints[gift].getY();

        final double signedArea = (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1);

        if(signedArea > 0) {
            return Answer.YES;
        }

        if (signedArea < 0) {
            return Answer.NO;
        }

        return Answer.IDK;
    }

    double normDistance(long giftId, long bestGiftId) {
        Point2D first = giftsPoints[Math.toIntExact(giftId)];
        Point2D second = giftsPoints[Math.toIntExact(bestGiftId)];

        double x1 = first.getX();
        double y1 = first.getY();

        double x2 = second.getX();
        double y2 = second.getY();

        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2)) / Math.sqrt(2.0);
    }
}
