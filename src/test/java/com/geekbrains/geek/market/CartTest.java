package com.geekbrains.geek.market;

import com.geekbrains.geek.market.entities.Product;
import com.geekbrains.geek.market.services.ProductService;
import com.geekbrains.geek.market.utils.Cart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

@SpringBootTest
@ActiveProfiles("h2")
public class CartTest {
    @Autowired
    private Cart cart;

    @MockBean
    private ProductService productService;

    @Test
    public void findOneUserTest() {
        Product product = new Product();
        product.setId(1L);
        product.setPrice(100);
        product.setTitle("Milk");

        Mockito.doReturn(Optional.of(product))
                .when(productService)
                .findById(1L);

        cart.addOrIncrement(1L);
        Assertions.assertEquals(1, cart.getItems().size());
    }
}
