package com.geekbrains.geek.market.repos

import com.geekbrains.geek.market.entities.Product
import com.geekbrains.geek.market.repositories.ProductRepository
import com.geekbrains.geek.market.repositories.specifications.ProductSpecifications
import com.geekbrains.geek.market.services.ProductService
import com.geekbrains.geek.market.utils.ProductFilter
import org.checkerframework.checker.units.qual.A
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@DataJpaTest
@ActiveProfiles("h2")
class ProductSpecificationTest extends Specification {

    @Autowired
    private ProductRepository productRepository

    def "should return non empty list of products"() {
        when :
            def list = productRepository.findAll()
        then :
            list.size() > 0
            println 'RESULT LIST: ' + list
    }

    def "should find only Bread" () {
        when :
            def paramMap = new HashMap(Map.of("max_price", "1"))
            def prod = productRepository.findAll(new ProductFilter(paramMap).spec,
                    PageRequest.of(0, 5))
        then :
            prod.getNumberOfElements() == 1
            println 'RESULT LIST: ' + prod.toList()
    }

}
