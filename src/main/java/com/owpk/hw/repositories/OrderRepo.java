package com.owpk.hw.repositories;

import com.owpk.hw.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {

  @Query("select o from Order o where o.customer.id = ?1")
  List<Order> findAllByCustomerId(Long customerId);

}
