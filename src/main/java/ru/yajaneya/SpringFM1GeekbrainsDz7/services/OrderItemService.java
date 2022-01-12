package ru.yajaneya.SpringFM1GeekbrainsDz7.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.yajaneya.SpringFM1GeekbrainsDz7.entities.OrderItem;
import ru.yajaneya.SpringFM1GeekbrainsDz7.repositories.OrderItemRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public List<OrderItem> findAll () {
        return orderItemRepository.findAll();
    }

    public Optional<OrderItem> findByID (Long id) {
        return orderItemRepository.findById(id);
    }

    public OrderItem save (OrderItem orderItem) {
        return orderItemRepository.save(orderItem);
    }

}
