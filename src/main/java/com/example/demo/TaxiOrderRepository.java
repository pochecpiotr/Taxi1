package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxiOrderRepository extends JpaRepository<TaxiOrder, Long> {
}
