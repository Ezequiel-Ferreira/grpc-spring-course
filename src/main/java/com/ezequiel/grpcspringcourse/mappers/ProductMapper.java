package com.ezequiel.grpcspringcourse.mappers;

import com.ezequiel.grpcspringcourse.dto.ProductInputDTO;
import com.ezequiel.grpcspringcourse.dto.ProductOutputDTO;
import com.ezequiel.grpcspringcourse.entities.Product;
import com.ezequiel.product.ProductRequest;
import com.ezequiel.product.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {

    public abstract ProductOutputDTO toOutputDTO(Product product);

    public abstract Product toEntity(ProductInputDTO dto);

    public abstract ProductInputDTO toInput(ProductRequest request);

    public abstract ProductResponse toResponse(ProductOutputDTO dto);

}
