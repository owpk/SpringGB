package com.owpk.hw.controllers;

import com.owpk.hw.entities.Product;
import com.owpk.hw.services.ProdService;
import com.owpk.hw.utils.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {
  private ProdService prodService;
  private Cart cart;

  @GetMapping
  private String getCart() {
    return "cart";
  }

  @GetMapping("/remove/{id}")
  private String deleteFromCart(@PathVariable Long id) {
    cart.remove(id);
    return "cart";
  }

  @GetMapping("/add/{product_id}")
  private void addToCart(@PathVariable(name = "product_id") Long id,
                         HttpServletRequest request,
                         HttpServletResponse response) throws IOException {
    Product p = prodService.findById(id);
    cart.addOrIncrement(p);
    response.sendRedirect(request.getHeader("referer"));
  }

  @GetMapping("/inc/{product_id}")
  public String addOrIncrementProduct(@PathVariable(name = "product_id") Long productId) {
    cart.incrementOnly(productId);
    return "redirect:/cart";
  }

  @GetMapping("/dec/{product_id}")
  public String decrementOrRemoveProduct(@PathVariable(name = "product_id") Long productId) {
    cart.decrementOrRemove(productId);
    return "redirect:/cart";
  }
}
