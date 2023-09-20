package com.example.demo.service;

import com.example.demo.model.TaxiOrder;
import com.example.demo.repository.TaxiOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaxiOrderService {

    private final TaxiOrderRepository taxiOrderRepository;

    @Autowired
    public TaxiOrderService(TaxiOrderRepository taxiOrderRepository) {
        this.taxiOrderRepository = taxiOrderRepository;
    }

    @Transactional
    public TaxiOrder placeOrder(String passengerName, String destination) {
        TaxiOrder order = new TaxiOrder();
        order.setPassengerName(passengerName);
        order.setDestination(destination);
        order.setCompleted(false);

        return taxiOrderRepository.save(order);
    }

    @Transactional
    public void completeOrder(Long orderId) {
        Optional<TaxiOrder> optionalOrder = taxiOrderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            TaxiOrder order = optionalOrder.get();
            order.setCompleted(true);
            taxiOrderRepository.save(order);
        }
    }

    @Transactional(readOnly = true)
    public List<TaxiOrder> getAllOrders() {
        return taxiOrderRepository.findAll();
    }
}
