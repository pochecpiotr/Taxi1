package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class TaxiOrderController {

    private final TaxiOrderService taxiOrderService;

    @Autowired
    public TaxiOrderController(TaxiOrderService taxiOrderService) {
        this.taxiOrderService = taxiOrderService;
    }

    @PostMapping("/place")
    public ResponseEntity<TaxiOrder> placeOrder(@RequestParam String passengerName, @RequestParam String destination) {
        TaxiOrder order = taxiOrderService.placeOrder(passengerName, destination);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }

    @PostMapping("/complete/{orderId}")
    public ResponseEntity<Void> completeOrder(@PathVariable Long orderId) {
        taxiOrderService.completeOrder(orderId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<TaxiOrder>> getAllOrders() {
        List<TaxiOrder> orders = taxiOrderService.getAllOrders();
        return ResponseEntity.ok(orders);
    }
}
