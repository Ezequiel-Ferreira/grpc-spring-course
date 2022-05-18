package com.ezequiel.grpcspringcourse.services;

import com.ezequiel.grpcspringcourse.dto.ProductOutputDTO;
import com.ezequiel.product.ProductRequest;
import com.ezequiel.product.ProductResponse;
import com.ezequiel.product.ProductResponseList;

import java.util.List;

public interface ProductService {
    ProductResponse create(ProductRequest request);
    ProductResponse findById(Long id);
    void delete(Long id);
    ProductResponseList findAll();

}
