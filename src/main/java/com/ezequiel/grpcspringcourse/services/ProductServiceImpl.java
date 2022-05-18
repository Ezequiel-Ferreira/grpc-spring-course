package com.ezequiel.grpcspringcourse.services;

import com.ezequiel.grpcspringcourse.entities.Product;
import com.ezequiel.grpcspringcourse.exceptions.product.ProductAlreadyExists;
import com.ezequiel.grpcspringcourse.exceptions.product.ProductNotFoundException;
import com.ezequiel.grpcspringcourse.mappers.ProductMapper;
import com.ezequiel.grpcspringcourse.mappers.ProductMapperImpl;
import com.ezequiel.grpcspringcourse.repositories.ProductRepository;
import com.ezequiel.product.ProductRequest;
import com.ezequiel.product.ProductResponse;
import com.ezequiel.product.ProductResponseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    private final ProductMapper mapper = new ProductMapperImpl();

    @Override
    public ProductResponse create(ProductRequest request) {
        validDuplicity(request);
        Product product = this.mapper.toEntity(this.mapper.toInput(request));
        product = repository.save(product);
        return this.mapper.toResponse(this.mapper.toOutputDTO(product));
    }

    @Override
    public ProductResponse findById(Long id) {
        Product product = findProduct(id);
        return this.mapper.toResponse(this.mapper.toOutputDTO(product));
    }

    @Override
    public void delete(Long id) {
        Product product = findProduct(id);
        this.repository.delete(product);
    }

    @Override
    public ProductResponseList findAll() {
        return ProductResponseList
                .newBuilder()
                .addAllProducts( findAllAndConvert())
                .build();
    }

    private List<ProductResponse> findAllAndConvert() {
        List<Product> products = repository.findAll();
        return products.stream()
                .map(mapper::toOutputDTO)
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    private void validDuplicity(ProductRequest request) {
        repository.findByNameIgnoreCase(request.getName())
                .ifPresent(product -> {
                    throw new ProductAlreadyExists(request.getName());
                });
    }

    private Product findProduct(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

}
