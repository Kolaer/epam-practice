package com.epam.practice.model.repositories;

import com.epam.practice.model.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GiftRepository extends JpaRepository<Gift, Long> {
    @Query("SELECT SUM(g.likes) FROM Gift g")
    Long getTotalLikes();
}
