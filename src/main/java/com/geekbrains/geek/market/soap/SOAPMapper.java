package com.geekbrains.geek.market.soap;

import com.geekbrains.geek.market.entities.Product;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper public interface SOAPMapper {
    SOAPMapper MAPPER = Mappers.getMapper(SOAPMapper.class);
    Product toCategory(ProductSOAP productSOAP);
    @InheritInverseConfiguration
    ProductSOAP fromProduct(Product product);
    List<Product> toProductList(List<ProductSOAP> productSOAPList);
    List<ProductSOAP> fromProductList(List<Product> products);
}

