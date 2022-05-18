package com.ezequiel.grpcspringcourse.exceptions.product;

import com.ezequiel.grpcspringcourse.exceptions.BaseBusinessException;
import io.grpc.Status;

public class ProductNotFoundException extends BaseBusinessException {

    private static final String ERROR_MESSAGE = "Produto com o ID %s não cadastrado no sistema.";

    private final Long id;

    public ProductNotFoundException(Long id) {
        super(String.format(ERROR_MESSAGE, id));
        this.id = id;
    }

    @Override
    public Status getStatusCode() {
        return Status.ALREADY_EXISTS;
    }

    @Override
    public String getErrorMessage() {
        return String.format(ERROR_MESSAGE, this.id);
    }
}
