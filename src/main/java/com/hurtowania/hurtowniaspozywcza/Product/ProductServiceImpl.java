package com.hurtowania.hurtowniaspozywcza.Product;

import com.hurtowania.hurtowniaspozywcza.Price.IPriceService;
import com.hurtowania.hurtowniaspozywcza.Price.Price;
import com.hurtowania.hurtowniaspozywcza.PriceLog.PriceLog;
import com.hurtowania.hurtowniaspozywcza.PriceLog.PriceLogRepository;
import com.hurtowania.hurtowniaspozywcza.Product.requests.CreateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {
    private final ProductRepository productRepository;
    private final IPriceService priceService;
    private final PriceLogRepository priceLogRepository;

    @Override
    public void addProduct(CreateProductRequest request) {
        Product product = Product.builder()
                .name(request.name())
                .category(request.category())
                .availableQuantity(request.quantity())
                .build();

        product = productRepository.save(product);

        PriceLog priceLog = PriceLog.builder()
            .prices(new ArrayList<>(Arrays.asList(request.price())))
            .times(new ArrayList<>(Arrays.asList(LocalDate.now())))
            .build();
        
        priceLog = priceLogRepository.save(priceLog);

        Price price = Price.builder()
                .price(request.price())
                .lowestFrom30Days(request.price())
                .history(priceLog)
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
                
                productPrice.getHistory().getPrices().add(price);
                productPrice.getHistory().getTimes().add(LocalDate.now());
                List<LocalDate> historyTimes = productPrice.getHistory().getTimes();
                List<Double> historyPrices = productPrice.getHistory().getPrices();

                LocalDate today = LocalDate.now();
                LocalDate thirtyDaysAgo = today.minusDays(30);

                double minPriceInLast30Days = Double.MAX_VALUE;

                for (int i = 0; i < historyTimes.size(); i++) {
                    LocalDate date = historyTimes.get(i);
                    if (!date.isBefore(thirtyDaysAgo) && !date.isAfter(today)) {
                        double currentPrice = historyPrices.get(i);
                        if (currentPrice < minPriceInLast30Days) {
                            minPriceInLast30Days = currentPrice;
                        }
                    }
                }

                if (minPriceInLast30Days == Double.MAX_VALUE) {
                    minPriceInLast30Days = price;
                }
                
                productPrice.setLowestFrom30Days(minPriceInLast30Days);
                productPrice.setPrice(price);

                priceService.savePrice(productPrice);

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
