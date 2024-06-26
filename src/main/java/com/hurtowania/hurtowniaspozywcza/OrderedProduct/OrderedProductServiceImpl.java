package com.hurtowania.hurtowniaspozywcza.OrderedProduct;

import com.hurtowania.hurtowniaspozywcza.Order.Order;
import com.hurtowania.hurtowniaspozywcza.Product.IProductService;
import com.hurtowania.hurtowniaspozywcza.Product.Product;
import com.hurtowania.hurtowniaspozywcza.Product.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class OrderedProductServiceImpl implements IOrderedProductService {
    private final OrderedProductRepository orderedProductRepository;
    private final ProductRepository productRepository;


    @Override
    public OrderedProduct createOrderedProduct(Order order, long productId, int quantity) {
        Product product = productRepository.findById(productId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(product.getAvailableQuantity() < quantity){
            //Przekroczono dostępną ilosc w magazynie
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        OrderedProduct orderedProduct = OrderedProduct.builder()
                .id(new OrderedProductId(order.getId(), productId))
                .order(order)
                .product(product)
                .quantity(quantity)
                .price(product.getPrice().getPrice()*quantity)
                .build();

        return orderedProductRepository.save(orderedProduct);
    }

    @Override
    public void deleteOrderedProduct(OrderedProduct orderedProduct) {
        orderedProductRepository.delete(orderedProduct);
    }
}
