package com.owpk.hw.controllers;

import com.owpk.hw.services.ProductService;
import com.owpk.hw.utils.ProdFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@AllArgsConstructor
public class Main {

  private ProductService productService;

  @GetMapping("/prod")
  private String get(@RequestParam(name = "p", defaultValue = "0") Integer page,
                     @RequestParam Map<String, String> params, Model model) {
    ProdFilter filter = new ProdFilter(params);
    filter.buildSpec();

    model.addAttribute("products",
        productService.getAll(PageRequest.of(page, 5), filter.getSpec()));

    model.addAttribute("filtered_attributes",
        filter.getFilteredAttributes());
    return "prod";
  }
}
