package com.owpk.hw.controllers;

import com.owpk.hw.entities.Product;
import com.owpk.hw.services.ProductService;
import com.owpk.hw.utils.ProdFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class Main {
  private static final int PAGINATION_SIZE = 3;

  private ProductService productService;

  @GetMapping("/prod")
  private String get(@RequestParam(name = "p") Optional<Integer> page,
                     @RequestParam Map<String, String> params,
                     Model model) {
    ProdFilter filter = new ProdFilter(params);
    filter.buildSpec();
    int currentPage = page.orElse(1);
    currentPage = currentPage < 0 ? 1 : currentPage;

    Page<Product> products = productService.getAll(PageRequest.of(currentPage - 1, 5), filter.getSpec());

    model.addAttribute("products", products);

    model.addAttribute("filtered_attributes", filter.getFilteredAttributes());

    model.addAttribute("pages",
        getPages(products.getNumber(), products.getTotalPages()));
    return "prod";
  }

  @Autowired
  public void setProductService(ProductService productService) {
    this.productService = productService;
  }

  // разделяет весь Page<T> на интервалы по PAGINATION_SIZE, находит необходимый интервал страниц
  // в котором находится текущая страница, заполняет и отдает список List<Integer> pages, тоесть необходимый интервал,
  // который передается в модель.
  private List<Integer> getPages(int current, int total) {
    int[][] diapasons = new int[(int) Math.ceil(total / (float) PAGINATION_SIZE)][];
    int offset = 0;
    List<Integer> pages = null;
    for (int i = 0; i < diapasons.length; i++) {
      diapasons[i] = new int[2];
      for (int j = 0; j < 2; j++) {
        diapasons[i][j] = offset;
        if (j == 0)
          offset += PAGINATION_SIZE;
      }
      if (current >= diapasons[i][0] && current <= diapasons[i][1]) {
        pages = IntStream.range(diapasons[i][0] + 1,
            diapasons[i][0] + PAGINATION_SIZE > total ? total + 1 : diapasons[i][1] + 1)
            .boxed()
            .collect(Collectors.toList());
      }
    }
    return pages;
  }

  @GetMapping("/prod/delete/{id}")
  private String delete(@PathVariable Long id,
                        @RequestParam Map<String, String> params) {
    productService.deleteById(id);
    params.forEach((k,v) -> System.out.println(k + " : " + v));
    return "redirect:/prod" +
        (params.size() > 0 ? params.keySet().stream()
        .map(k -> k + "=" + params.get(k)).reduce("?", (p1,p2) -> p1 + "&" + p2)
        : "");
  }
}
