package ru.yajaneya.SpringFM1GeekbrainsDz7.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
}
