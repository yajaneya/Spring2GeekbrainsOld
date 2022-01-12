package ru.yajaneya.SpringFM1GeekbrainsDz7.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yajaneya.SpringFM1GeekbrainsDz7.dto.Cart;
import ru.yajaneya.SpringFM1GeekbrainsDz7.entities.Order;
import ru.yajaneya.SpringFM1GeekbrainsDz7.entities.OrderItem;
import ru.yajaneya.SpringFM1GeekbrainsDz7.entities.User;
import ru.yajaneya.SpringFM1GeekbrainsDz7.exceptions.AppError;
import ru.yajaneya.SpringFM1GeekbrainsDz7.services.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final UserService userService;
    private final CartService cartService;
    private final ProductService productService;


    @GetMapping
    public List<Order> getOrders () {
        return orderService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> saveNewOrder (@RequestParam String address, @RequestParam String phone, Principal principal) {

        User user = userService.findByUsername(principal.getName()).get();
        Cart cart = cartService.getCurrentCart();

        if (cart.getItems().isEmpty()) {
            return new ResponseEntity<>(new AppError
                    ("Корзина пуста", HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
        }

        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(cart.getTotalPrice());
        order.setAddress(address);
        order.setPhone(phone);
        Order newOrder = orderService.save(order);

        cart.getItems().forEach(i -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(productService.findByID(i.getProductId()).get());
            orderItem.setOrder(newOrder);
            orderItem.setQuantity(i.getQuantity());
            orderItem.setPricePerProduct(i.getPricePerProduct());
            orderItem.setPrice(i.getPrice());
            orderItemService.save(orderItem);
        });

        return ResponseEntity.ok(order);
    }
}
