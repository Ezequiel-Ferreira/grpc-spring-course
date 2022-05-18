package com.ezequiel.grpcspringcourse.controllers;
import com.ezequiel.HelloReq;
import com.ezequiel.HelloRes;
import com.ezequiel.HelloServiceGrpc;
import com.ezequiel.grpcspringcourse.entities.Hello;
import com.ezequiel.grpcspringcourse.repositories.HelloRepository;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class HelloController extends HelloServiceGrpc.HelloServiceImplBase{
    @Autowired
    private HelloRepository repository;

    @Override
    public void hello(HelloReq request, StreamObserver<HelloRes> responseObserver) {
        Hello h = repository.save(new Hello(null, request.getMessage()));
        var response = HelloRes.newBuilder()
                .setId(h.getId())
                .setMessage(h.getValue())
                .build();
        ;
        responseObserver.onNext(response);
        responseObserver.onCompleted();

    }
}
