package com.epam.practice.model.repositories;

import com.epam.practice.model.Answers;
import com.epam.practice.model.AnswersKey;
import com.epam.practice.model.Gift;
import com.epam.practice.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AnswersRepository extends JpaRepository<Answers, AnswersKey> {
    @Modifying
    @Query("UPDATE Answers a SET a.answerYes = a.answerYes + 1 WHERE a.gift = ?1 AND a.question = ?2")
    void incYes(Gift gift, Question question);

    @Modifying
    @Query("UPDATE Answers a SET a.answerNo = a.answerNo + 1 WHERE a.gift = ?1 AND a.question = ?2")
    void incNo(Gift gift, Question question);

    @Modifying
    @Query("UPDATE Answers a SET a.answerIdk = a.answerIdk + 1 WHERE a.gift = ?1 AND a.question = ?2")
    void incIDK(Gift gift, Question question);
}
