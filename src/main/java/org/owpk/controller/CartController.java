package org.owpk.controller;

import org.owpk.model.ProdRepresentation;
import org.owpk.repos.CategoryRepo;
import org.owpk.repos.ProductRepo;
import org.owpk.service.ProductService;
import org.owpk.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {
  private Service productService;
  private CategoryRepo categoryRepo;

  @Autowired
  public void setProductService(ProductService productService) {
    this.productService = productService;
  }

  @Autowired
  public void setCategoryService(CategoryRepo categoryRepo) {
    this.categoryRepo = categoryRepo;
  }

  @GetMapping("/cart")
  private String getCart(Model model) {
    model.addAttribute("cart", productService.getAllRepresentation());
    model.addAttribute("categories", categoryRepo.getAll());
    return "cart";
  }

  @GetMapping("/add")
  public String addNewBox(@RequestParam String name,
                          @RequestParam int cost,
                          @RequestParam String category) {
    ProdRepresentation repr =
        new ProdRepresentation(ProductRepo.get(), name, cost, category);
    productService.insert(repr);
    return "redirect:/cart";
  }

  @GetMapping("/delete/{id}")
  public String delete(@PathVariable int id) {
    productService.deleteById(id);
    return "redirect:/cart";
  }

}
