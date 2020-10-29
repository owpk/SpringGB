package com.owpk.hw.controllers;

import com.owpk.hw.dto.ProductDto;
import com.owpk.hw.entities.Product;
import com.owpk.hw.entities.User;
import com.owpk.hw.exceptions.ExceptionForPage;
import com.owpk.hw.exceptions.ResourceNotFoundException;
import com.owpk.hw.repositories.specifications.ProdSpec;
import com.owpk.hw.services.ProductService;
import com.owpk.hw.services.UserService;
import com.owpk.hw.utils.Cart;
import com.owpk.hw.utils.ProductFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {
    private ProductService productService;
    private Cart cart;
    private UserService userService;

    @GetMapping
    private List<ProductDto> getAllProducts(@RequestParam(name = "p", defaultValue = "1", required = false) Integer page,
                                            @RequestParam Map<String, String> params) {
        if (page == null || page < 1) page = 1;
        ProductFilter productSpec = new ProductFilter(params);
        Specification<Product> specification = productSpec.getSpec();
        Pageable var = PageRequest.of(page - 1, 5);
        Page<Product> products =  productService.getProductBySpec(specification, var);
        if (products.getNumberOfElements() == 0) throw new ResourceNotFoundException("empty!!");
        return products
                .get()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }


    //Filter by categories only test
    @GetMapping("/all")
    private List<ProductDto> getAllBySpecificCategory(@RequestParam(name = "category") String category) {
        return productService.getAllBySpec(ProdSpec.categoryLike(category))
                .stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/dao")
    public String daoTestPage(Principal principal) {
        // Authentication a = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(principal.getName());
        if (user == null)
            throw new RuntimeException("unable to fing user by username: " + principal.getName());
        return "authenticated: " + user.getUsername() + " : " + user.getEmail();
    }
}
