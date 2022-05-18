package com.ezequiel.grpcspringcourse.repositories;

import com.ezequiel.grpcspringcourse.entities.Hello;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface HelloRepository extends JpaRepositoryImplementation<Hello, Long> {
}
