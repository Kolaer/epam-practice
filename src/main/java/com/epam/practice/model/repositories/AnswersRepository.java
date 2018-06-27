package com.epam.practice.model.repositories;

import com.epam.practice.model.Answers;
import com.epam.practice.model.AnswersKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswersRepository extends JpaRepository<Answers, AnswersKey> {
}
