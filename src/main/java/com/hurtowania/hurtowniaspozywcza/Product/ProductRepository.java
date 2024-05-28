package com.hurtowania.hurtowniaspozywcza.Product;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Product findByName(String name);
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
