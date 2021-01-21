package com.geekbrains.geek.market.repositories;

import com.geekbrains.geek.market.entities.Acc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Acc, Long> {

    @Query("select p from Acc p where p.user.username = ?1")
    Optional<Acc> findByUserName(String username);
}
