package com.owpk.hw.utils;

import com.owpk.hw.entities.Customer;
import com.owpk.hw.entities.Order;
import com.owpk.hw.services.CustomerService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
@Data
public class UserSession {
  private List<Order> orderList;
  private Customer customer;
  private CustomerService customerService;

  @Autowired
  public void setCustomerService(CustomerService customerService) {
    this.customerService = customerService;
  }

  @PostConstruct
  private void init() {
    orderList = new ArrayList<>();
    customer = customerService.getCustomerById(1L); //temp stub
  }

  public void addOrder(Order o) {
    orderList.add(o);
  }
}
