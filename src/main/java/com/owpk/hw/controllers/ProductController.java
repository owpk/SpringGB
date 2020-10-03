package com.owpk.hw.controllers;

import com.owpk.hw.entities.Product;
import com.owpk.hw.services.ProdService;
import com.owpk.hw.utils.ProdFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/prod")
public class ProductController {

  private ProdService prodService;

  @GetMapping
  private String getProd(
      @RequestParam(value = "p") Optional<Integer> page,
      @RequestParam Map<String, String> params, Model model) {
    int currPage = page.orElse(1);
    Page<Product> products = prodService.getAllProds(
        new ProdFilter().getSpecAndCreateAttributeDefinition(params),
        PageRequest.of(currPage - 1, 5));
    model.addAttribute("products", products);
    return "prod";
  }

  @GetMapping("/edit/{id}")
  private String edit(@PathVariable Long id, Model model) {
    Product p = prodService.findById(id);
    System.out.println(p);
    model.addAttribute("item", p);
    return "edit";
  }

  @PostMapping("/edit")
  private String edited(@ModelAttribute Product product) {
    prodService.saveOrUpdate(product);
    System.out.println(product);
    return "redirect:/prod";
  }

}