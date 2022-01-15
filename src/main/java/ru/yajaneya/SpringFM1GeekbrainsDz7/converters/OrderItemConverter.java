package ru.yajaneya.SpringFM1GeekbrainsDz7.converters;

import org.springframework.stereotype.Component;
import ru.yajaneya.SpringFM1GeekbrainsDz7.dto.OrderItemDto;
import ru.yajaneya.SpringFM1GeekbrainsDz7.entities.OrderItem;

@Component
public class OrderItemConverter {
    public OrderItem dtoToEntity(OrderItemDto orderItemDto) {
        throw new UnsupportedOperationException();
    }

    public OrderItemDto entityToDto(OrderItem orderItem) {
        return new OrderItemDto(orderItem.getProduct().getId(), orderItem.getProduct().getTitle(), orderItem.getQuantity(), orderItem.getPricePerProduct(), orderItem.getPrice());
    }
}
