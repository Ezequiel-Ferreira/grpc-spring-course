package com.ezequiel.grpcspringcourse.repositories;

import com.ezequiel.grpcspringcourse.entities.Hello;
import com.ezequiel.grpcspringcourse.entities.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

import java.util.Optional;

public interface ProductRepository extends JpaRepositoryImplementation<Product, Long> {

    Optional<Product> findByNameIgnoreCase(String name);
}
