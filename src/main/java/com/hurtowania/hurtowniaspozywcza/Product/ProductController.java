package com.hurtowania.hurtowniaspozywcza.Product;

import com.hurtowania.hurtowniaspozywcza.Product.requests.CreateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final IProductService productService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addProduct(@RequestBody CreateProductRequest request){
        productService.addProduct(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int id){
        productService.deleteProduct(id);
    }
}
