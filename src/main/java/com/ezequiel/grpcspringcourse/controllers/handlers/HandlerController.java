package com.ezequiel.grpcspringcourse.controllers.handlers;

import com.ezequiel.grpcspringcourse.exceptions.BaseBusinessException;
import io.grpc.StatusRuntimeException;
import net.devh.boot.grpc.server.advice.GrpcAdvice;
import net.devh.boot.grpc.server.advice.GrpcExceptionHandler;

@GrpcAdvice
public class HandlerController {

    @GrpcExceptionHandler(BaseBusinessException.class)
    public StatusRuntimeException handleBusinessException(BaseBusinessException e) {
        return e.getStatusCode()
                .withDescription(e.getErrorMessage())
                .asRuntimeException();
    }

}
