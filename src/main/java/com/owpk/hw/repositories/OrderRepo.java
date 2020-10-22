package com.owpk.hw.repositories;

import com.owpk.hw.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Long, Order> {
}
