package com.hurtowania.hurtowniaspozywcza.Product;

import com.hurtowania.hurtowniaspozywcza.Price.IPriceService;
import com.hurtowania.hurtowniaspozywcza.Price.Price;
import com.hurtowania.hurtowniaspozywcza.Product.requests.CreateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;
    private final IPriceService priceService;


    @Override
    public void addProduct(CreateProductRequest request) {
        Product product = Product.builder()
                .name(request.name())
                .category(request.category())
                .availableQuantity(request.quantity())
                .build();

        product = productRepository.save(product);

        Price price = Price.builder()
                .price(request.price())
                .lowestFrom30Days(request.price())
                .product(product)
                .build();
        priceService.savePrice(price);
    }

    @Override
    public void deleteProduct(long productId) {
        productRepository.deleteById(productId);
    }



}
