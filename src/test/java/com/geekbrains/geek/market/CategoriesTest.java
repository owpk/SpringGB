package com.geekbrains.geek.market;


import com.geekbrains.geek.market.dto.CategoryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("h2")
public class CategoriesTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    @WithMockUser(username = "Bob", roles = "USER")
    public void getCategoriesListTest() {
        String e = restTemplate.getForEntity("/api/v1/categories", ResponseEntity.class).getBody().toString();

        List<CategoryDto> categories = restTemplate.getForObject("/api/v1/categories", List.class);
        assertThat(categories).isNotNull();
        assertThat(categories).isNotEmpty();
    }
}
