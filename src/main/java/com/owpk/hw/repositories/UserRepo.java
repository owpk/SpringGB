package com.owpk.hw.repositories;

import com.owpk.hw.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<Long, User> {
}
