package com.hurtowania.hurtowniaspozywcza.Product;

import java.util.List;

import org.springframework.data.domain.Page;

import com.hurtowania.hurtowniaspozywcza.Product.requests.CreateProductRequest;

public interface IProductService {
    void addProduct(CreateProductRequest request);
    // void deleteProduct(long productId);
    boolean makeArchived(long id);
    Product getProductById(long id);
    Page<Product> getProductByName(String name, int pageNo, int pageSize);
    Page<Product> getProductByCategory(ProductCategory category, int pageNo, int pageSize);
    boolean updateProductPriceById(long id, double price);
    boolean updateProductAmountById(long id, int amount);
    Page<Product> getProduct(int pageNo, int pageSize);
}
