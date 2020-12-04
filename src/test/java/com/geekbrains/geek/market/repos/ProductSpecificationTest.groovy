package com.geekbrains.geek.market.repos

import com.geekbrains.geek.market.repositories.ProductRepository
import com.geekbrains.geek.market.utils.ProductFilter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@DataJpaTest
@ActiveProfiles("h2")
class ProductSpecificationTest extends Specification {

    @Autowired
    private ProductRepository productRepository

    def "should return non empty list of products"() {
        when:
        def list = productRepository.findAll()
        then:
        list.size() > 0
        println 'RESULT LIST: ' + list
    }

    def "should find only Bread"() {
        expect:
        def paramMap = new HashMap(Map.of(key, val))
        def prod = productRepository.findAll(new ProductFilter(paramMap).spec,
                PageRequest.of(0, 5))
        prod.getNumberOfElements() == 1
        where:
        key         | val
        "max_price" | "1"
        "title"     | "Bread"
    }
}
