package com.hurtowania.hurtowniaspozywcza.Product;

import com.hurtowania.hurtowniaspozywcza.Price.IPriceService;
import com.hurtowania.hurtowniaspozywcza.Price.Price;
import com.hurtowania.hurtowniaspozywcza.Product.requests.CreateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Product product = productRepository.findById(productId).orElse(null);
        if (product != null) {
            priceService.deletePrice(product.getPrice());
            productRepository.deleteById(productId);
        }

    }

    @Override
    public List<Product> getProduct(){
        List<Product> product = productRepository.findAll();
        return product;
    }

    @Override
    public Product getProductById(long id){
        Optional<Product> optionalProduct = productRepository.findById(id);
        return optionalProduct.orElse(null);
    }

    @Override
    public Product getProductByName(String name){
        return productRepository.findByName(name);
    }

    @Override
    public boolean updateProductPriceById(long id, double price) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            Price productPrice = product.getPrice();
            if (productPrice != null) {
                if (productPrice.getLowestFrom30Days() > price){
                    productPrice.setLowestFrom30Days(price);
                }
                productPrice.setPrice(price);
                productRepository.save(product);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean updateProductAmountById(long id, int amount) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setAvailableQuantity(amount);
            productRepository.save(product);
            return true;
        }
        return false;
    }
}
