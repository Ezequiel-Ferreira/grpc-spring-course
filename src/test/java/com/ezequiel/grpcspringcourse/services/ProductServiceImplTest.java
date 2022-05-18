package com.ezequiel.grpcspringcourse.services;

import com.ezequiel.grpcspringcourse.entities.Product;
import com.ezequiel.grpcspringcourse.exceptions.product.ProductAlreadyExists;
import com.ezequiel.grpcspringcourse.exceptions.product.ProductNotFoundException;
import com.ezequiel.grpcspringcourse.repositories.ProductRepository;
import com.ezequiel.product.ProductRequest;
import com.ezequiel.product.ProductResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
public class ProductServiceImplTest {

    @Mock
    private ProductRepository repository;

    @InjectMocks
    private ProductServiceImpl service;

    private Product product;

    private ProductRequest request;

    private ProductResponse response;

    @BeforeEach
    public void initialize() {
        this.product = new Product(1L, "Arroz", 2.03, 200);
        this.request = ProductRequest.newBuilder()
                .setName("Arroz")
                .setPrice(2D)
                .setQuantityInStock(20)
                .build();
        this.response = ProductResponse.newBuilder()
                .setId(1)
                .setName("Arroz")
                .setPrice(2.03)
                .setQuantityInStock(200)
                .build();
    }

    @Test
    @DisplayName("when given a product request, the product must be saved and returned")
    public void shouldBeSaveProductAndReturnProductResponse() {
        when(repository.save(any())).thenReturn(product);
        Assertions.assertEquals(service.create(request), response);
    }

    @Test
    @DisplayName("when given a product with name registered, an exception must be thrown")
    public void shouldThrowExceptionAlreadyExists() {
        when(repository.findByNameIgnoreCase(any())).thenReturn(Optional.of(product));
        Assertions.assertThrows(ProductAlreadyExists.class, () -> {
            service.create(request);
        });
    }

    @Test
    @DisplayName("when given a product's id, an product with the same id must be fetched and returned")
    public void shouldFetchProductAndReturnById() {
        Optional<Product> productOptional = Optional.of(product);
        when(repository.findById(any())).thenReturn(productOptional);
        Assertions.assertEquals(service.findById(1L), response);
    }

    @Test
    @DisplayName("when given a product's id and and no product is found, an exception must be thrown")
    public void shouldThrowExceptionNotFound() {
        Assertions.assertThrows(ProductNotFoundException.class, () -> {
            service.findById(-1L);
        });
    }


}
