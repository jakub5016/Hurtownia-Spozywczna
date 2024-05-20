package com.hurtowania.hurtowniaspozywcza.Product;

import java.util.List;

import com.hurtowania.hurtowniaspozywcza.Product.requests.CreateProductRequest;

public interface IProductService {
    void addProduct(CreateProductRequest request);
    void deleteProduct(long productId);
    List<Product> getProduct();
    Product getProductById(long id);
    Product getProductByName(String name);
    boolean updateProductPriceById(long id, double price);
    boolean updateProductAmountById(long id, int amount);
}
