package com.hurtowania.hurtowniaspozywcza;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduktRepository extends JpaRepository<Produkt, Long>{
    Optional<Produkt> findById(Long id);
}
