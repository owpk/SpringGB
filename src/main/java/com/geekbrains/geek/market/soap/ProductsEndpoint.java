package com.geekbrains.geek.market.soap;

import com.geekbrains.geek.market.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@RequiredArgsConstructor
@Endpoint
public class ProductsEndpoint {
    private static final String NAMESPACE_URI = "http://www.geekbrains.com/geek/market/soap";
    private final ProductSOAPService productSOAPService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductsRequest")
    @ResponsePayload
    public GetProductsResponse getCountry(@RequestPayload GetProductsRequest request) { //stub requestPayload
        GetProductsResponse response = new GetProductsResponse();
        List<ProductSOAP> list = productSOAPService.findAllAndConvert();
        response.setAllProducts(list);
        return response;
    }
}
