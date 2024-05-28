package com.hurtowania.hurtowniaspozywcza.Order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByClientId(long clientId);
    Page<Order> findByStatusIn(List<OrderStatus> statuses, Pageable pageable);
}
