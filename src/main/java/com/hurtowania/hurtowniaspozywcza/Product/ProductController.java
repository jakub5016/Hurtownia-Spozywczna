package com.hurtowania.hurtowniaspozywcza.Product;

import com.hurtowania.hurtowniaspozywcza.Price.requests.ReturnAllPricesDTO;
import com.hurtowania.hurtowniaspozywcza.Price.requests.ReturnBothPriceDTO;
import com.hurtowania.hurtowniaspozywcza.PriceLog.PriceLog;
import com.hurtowania.hurtowniaspozywcza.Product.requests.CreateProductRequest;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ProductController {
    private final IProductService productService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public void addProduct(@RequestBody CreateProductRequest request){
        productService.addProduct(request);
    }

    @PutMapping("/{id}/archive")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void makeArchived(@PathVariable int id){
        productService.makeArchived(id);
    }

    @GetMapping
    public ResponseEntity<Page<Product>> getProduct(@RequestParam(defaultValue = "0", required = false) int pageNo, @RequestParam(defaultValue = "10", required = false) int pageSize){
        return ResponseEntity.ok(productService.getProduct(pageNo, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable long id) {
        Product product = productService.getProductById(id);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/") 
    public ResponseEntity<Page<Product>> getProductByName(
        @RequestParam String name, @RequestParam(defaultValue = "0", required = false) int pageNo, @RequestParam(defaultValue = "10", required = false) int pageSize
        ) {
        Page<Product> product = productService.getProductByName(name, pageNo, pageSize);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/{id}/price")
    public ResponseEntity<ReturnBothPriceDTO> getPriceByProductId(@PathVariable long id) {
        Product product = productService.getProductById(id);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        double currentPrice = product.getPrice().getPrice();
        double priceFrom30Days = product.getPrice().getLowestFrom30Days();

        ReturnBothPriceDTO dto = ReturnBothPriceDTO.builder()
                                                    .currentPrice(currentPrice)
                                                    .priceFrom30Days(priceFrom30Days)
                                                    .build();

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/{id}/price/all")
    public ResponseEntity<ReturnAllPricesDTO> getAllPricesByProductId(@PathVariable long id) {
        Product product = productService.getProductById(id);

        if (product == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        double currentPrice = product.getPrice().getPrice();
        double priceFrom30Days = product.getPrice().getLowestFrom30Days();

        ReturnBothPriceDTO bothPrices = ReturnBothPriceDTO.builder()
                                                    .currentPrice(currentPrice)
                                                    .priceFrom30Days(priceFrom30Days)
                                                    .build();


        PriceLog logs = product.getPrice().getHistory();

        ReturnAllPricesDTO dto = ReturnAllPricesDTO.builder()
                                                    .prices(bothPrices)
                                                    .logs(logs)
                                                    .build();

        return new ResponseEntity<>(dto, HttpStatus.OK);
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
