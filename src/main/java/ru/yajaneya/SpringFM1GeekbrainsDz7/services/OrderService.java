package ru.yajaneya.SpringFM1GeekbrainsDz7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yajaneya.SpringFM1GeekbrainsDz7.entities.Order;
import ru.yajaneya.SpringFM1GeekbrainsDz7.repositories.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> findAll () {
        return orderRepository.findAll();
    }

    public Optional<Order> findByID (Long id) {
        return orderRepository.findById(id);
    }

    public Order save (Order order) {
        return orderRepository.save(order);
    }

}
