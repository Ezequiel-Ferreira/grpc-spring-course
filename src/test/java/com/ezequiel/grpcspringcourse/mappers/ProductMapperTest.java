package com.ezequiel.grpcspringcourse.mappers;

import com.ezequiel.grpcspringcourse.dto.ProductInputDTO;
import com.ezequiel.grpcspringcourse.entities.Product;
import com.ezequiel.product.ProductRequest;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
public class ProductMapperTest {

    private ProductMapper mapper = new ProductMapperImpl();

    @Test
    public void shouldMapProductRequestToProductInputDTO(){
        ProductRequest request = ProductRequest.newBuilder()
                .setName("Arroz")
                .setPrice(2D)
                .setQuantityInStock(20)
                .build();
        ProductInputDTO dto = mapper.toInput(request);

        Assert.assertNotNull(dto);
    }

    @Test
    public void shouldMapProductRequestToProductInputDTOToProduct(){
        ProductRequest request = ProductRequest.newBuilder()
                .setName("Arroz")
                .setPrice(2D)
                .setQuantityInStock(20)
                .build();
        ProductInputDTO dto = mapper.toInput(request);

        Product product = mapper.toEntity(dto);

        Assert.assertNotNull(product);
    }



}
