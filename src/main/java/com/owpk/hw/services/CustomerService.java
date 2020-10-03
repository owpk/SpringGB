package com.owpk.hw.services;

import com.owpk.hw.entities.Customer;
import com.owpk.hw.repositories.CustomerRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {
  private CustomerRepo customerRepo;

  public Customer getCustomerById(Long id) {
    return (Customer) customerRepo.findById(id)
        .orElseThrow(() -> new RuntimeException("Customer Not Found"));
  }
}
