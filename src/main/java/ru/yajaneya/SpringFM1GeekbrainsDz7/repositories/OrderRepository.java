package ru.yajaneya.SpringFM1GeekbrainsDz7.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.yajaneya.SpringFM1GeekbrainsDz7.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
