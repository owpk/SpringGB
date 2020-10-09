package com.owpk.hw.controllers;

import com.owpk.hw.entities.Product;
import com.owpk.hw.services.ProductService;
import com.owpk.hw.utils.ProdFilter;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;

    @GetMapping
    public String showAllProducts(Model model,
                                  @RequestParam(defaultValue = "1", name = "p") Integer page,
                                  @RequestParam Map<String, String> params
                                  ) {
        if (page < 1) {
            page = 1;
        }
        ProdFilter productFilter = new ProdFilter();
        productFilter.buildSpecAndAttributes(params);
        Page<Product> products = productService.findAll(productFilter.getSpec(), page - 1, 5);
        model.addAttribute("products", products);
        model.addAttribute("filterDefinition", productFilter.getAttributes());
        return "products";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Product getOneProductById(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new RuntimeException("Product with id: " + id + " doesn't exists"));
    }

    @PostMapping("/showme")
    public void showMeObject(@RequestBody Product p) {
        System.out.println(p);
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product p = productService.findById(id).orElseThrow(() -> new RuntimeException("Product with id: " + id + " doesn't exists (for edit)"));
        model.addAttribute("product", p);
        return "edit_product";
    }

    @PostMapping("/edit")
    public String showEditForm(@ModelAttribute Product product) {
        productService.addNew(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String deleteOneProductById(@PathVariable Long id) {
        productService.deleteById(id);
        return "ok";
    }
}
