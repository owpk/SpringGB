package com.geekbrains.geek.market


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("h2")
class ProductRestControllerTest extends Specification {

//    @Shared
//    private def prods

    @Autowired
    private TestRestTemplate restTemplate;

    @WithMockUser(username = "user", roles = "USER")
    def "product rest endpoint test"() {
        expect :
            def prods = restTemplate.getForObject("/api/v1/products", List.class)
            prods != null
            prods.size() != 0
    }
    
}
