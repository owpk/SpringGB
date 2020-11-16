package com.geekbrains.geek.market.soap;

import com.geekbrains.geek.market.entities.Product;
import com.geekbrains.geek.market.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductSOAPService {
    private final ProductRepository productRepository;

    public List<ProductSOAP> findAllAndConvert() {
        List<Product> originList = productRepository.findAll();
        return SOAPMapper.MAPPER.fromProductList(originList);
    }
}
