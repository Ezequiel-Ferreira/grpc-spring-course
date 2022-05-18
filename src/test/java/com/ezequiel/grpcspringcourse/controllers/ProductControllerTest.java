package com.ezequiel.grpcspringcourse.controllers;

import com.ezequiel.grpcspringcourse.entities.Product;
import com.ezequiel.product.ProductRequest;
import com.ezequiel.product.ProductResponse;
import com.ezequiel.product.ProductServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource("classpath:application-test.properties")
@DirtiesContext
public class ProductControllerTest {

    @GrpcClient("inProcess")
    private ProductServiceGrpc.ProductServiceBlockingStub blockingStub;

    private ProductRequest request;

    private ProductResponse response;

    @BeforeEach
    public void initialize() {
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
    @DisplayName("")
    public void shouldCreateProductAndReturn() {
        ProductResponse productResponse = blockingStub.create(request);

        Assertions.assertEquals(request.getName(), productResponse.getName());
        Assertions.assertEquals(request.getPrice(), productResponse.getPrice());
        Assertions.assertEquals(request.getQuantityInStock(), productResponse.getQuantityInStock());
    }
}
