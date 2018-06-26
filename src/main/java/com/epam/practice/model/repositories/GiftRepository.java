package com.epam.practice.model.repositories;

import com.epam.practice.model.Gift;
import org.springframework.data.repository.CrudRepository;

public interface GiftRepository extends CrudRepository<Gift, Long> {
}
