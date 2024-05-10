package com.hurtowania.hurtowniaspozywcza.Product;

import com.hurtowania.hurtowniaspozywcza.Product.requests.CreateProductRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public List<Product> getProduct(){
        return productService.getProduct();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        Product product = productService.getProductById(id);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/product?name={name}") //to do poprawy
    public ResponseEntity<Product> getProductByName(@RequestParam String name) {
        Product product = productService.getProductByName(name);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/{id}/price")
    public ResponseEntity<Double> getPriceByProductId(@PathVariable long id) {
        Product product = productService.getProductById(id);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        double price = product.getPrice().getPrice();

        return new ResponseEntity<>(price, HttpStatus.OK);
    }

    @PutMapping("/{id}/price")
    public ResponseEntity<String> updateProductPriceById(@PathVariable long id, @RequestParam double price) {
        boolean updated = productService.updateProductPriceById(id, price);

        if (updated) {
            return new ResponseEntity<>("Product price updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update product price", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}/amount")
    public ResponseEntity<String> updateProductAmountById(@PathVariable long id, @RequestParam int amount) {
        boolean updated = productService.updateProductAmountById(id, amount);

        if (updated) {
            return new ResponseEntity<>("Product amount updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update product amount", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
