package com.hurtowania.hurtowniaspozywcza.Order;

import com.hurtowania.hurtowniaspozywcza.Order.requests.CreateOrderRequest;
import com.hurtowania.hurtowniaspozywcza.OrderedProduct.OrderedProduct;
import com.hurtowania.hurtowniaspozywcza.Product.ProductDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final IOrderService orderService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createOrder(@RequestBody CreateOrderRequest request) {
        orderService.addOrder(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable long id) {
        Order order = orderService.getOrderById(id);

        if (order == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<String> updateOrderStatusById(@PathVariable long id, @RequestParam("status") OrderStatus status) {
        boolean updated = orderService.updateOrderStatusById(id, status);

        if (updated) {
            return new ResponseEntity<>("Order status updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update order status", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String/*Order*/> updateOrderedProducts(@PathVariable long id, @RequestBody List<ProductDTO> orderedProductUpdates) {
        boolean/*Order*/ updated = orderService.updateOrderedProducts(id, orderedProductUpdates);

        // if (updated == null) {
        //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // }

        // return new ResponseEntity<>(updated, HttpStatus.OK);

        if (updated) {
            return new ResponseEntity<>("Ordered products updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update ordered products", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
