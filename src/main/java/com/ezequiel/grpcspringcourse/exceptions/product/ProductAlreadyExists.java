package com.ezequiel.grpcspringcourse.exceptions.product;

import com.ezequiel.grpcspringcourse.exceptions.BaseBusinessException;
import io.grpc.Status;

public class ProductAlreadyExists extends BaseBusinessException {

    private static final String ERROR_MESSAGE = "Produto %s já cadastrado no sistema.";

    private final String name;

    public ProductAlreadyExists(String name) {
        super(String.format(ERROR_MESSAGE, name));
        this.name = name;
    }

    @Override
    public Status getStatusCode() {
        return Status.ALREADY_EXISTS;
    }

    @Override
    public String getErrorMessage() {
        return String.format(ERROR_MESSAGE, this.name);
    }
}
