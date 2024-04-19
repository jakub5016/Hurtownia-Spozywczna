package com.hurtowania.hurtowniaspozywcza.Product;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/product")
public class ProductAPI {
    @Autowired
    ProductRepository repository;

    @GetMapping("/")
    public List<Product> getAllProducts() {
        return repository.findAll();
    }
    
}
