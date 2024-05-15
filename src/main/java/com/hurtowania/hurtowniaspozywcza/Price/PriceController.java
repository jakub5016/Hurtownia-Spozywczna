package com.hurtowania.hurtowniaspozywcza.Price;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/price")
@RequiredArgsConstructor
public class PriceController {
    private final IPriceService priceService;

    @GetMapping("/{id}")
    public ResponseEntity<Price> getPriceById(@PathVariable long id) {
        Price price = priceService.getPriceById(id);

        if (price == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(price, HttpStatus.OK);
    }
    
}
