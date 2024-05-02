package com.hurtowania.hurtowniaspozywcza.OrderedProduct;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedProductRepository extends CrudRepository<OrderedProduct, OrderedProductId> {
}
