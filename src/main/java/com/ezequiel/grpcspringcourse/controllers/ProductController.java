package com.ezequiel.grpcspringcourse.controllers;

import com.ezequiel.grpcspringcourse.dto.ProductOutputDTO;
import com.ezequiel.grpcspringcourse.services.ProductService;
import com.ezequiel.product.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@GrpcService
public class ProductController extends ProductServiceGrpc.ProductServiceImplBase {

    @Autowired
    private ProductService service;

    @Override
    public void create(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {
        responseObserver.onNext(this.service.create(request));
        responseObserver.onCompleted();
    }

    @Override
    public void findById(RequestById request, StreamObserver<ProductResponse> responseObserver) {
        responseObserver.onNext(this.service.findById(request.getId()));
        responseObserver.onCompleted();
    }

    @Override
    public void delete(RequestById request, StreamObserver<EmptyResponse> responseObserver) {
        this.service.delete(request.getId());
        responseObserver.onNext(EmptyResponse.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void findAll(EmptyRequest request, StreamObserver<ProductResponseList> responseObserver) {
        responseObserver.onNext(service.findAll());
        responseObserver.onCompleted();
    }
}
