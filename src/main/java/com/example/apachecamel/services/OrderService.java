package com.example.apachecamel.services;

import com.example.apachecamel.dtos.Order;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private static List<Order> list = new ArrayList<>();

    @PostConstruct
    public void initDb() {
        list.add(new Order(56, "Mobile", 5000));
        list.add(new Order(355, "Book", 234));
        list.add(new Order(1677, "Pen", 20));
        list.add(new Order(900, "Cup", 456));
        list.add(new Order(12, "Shoes", 2500));
    }

    public Order addOrder(Order order) {
        list.add(order);
        return order;
    }

    public List<Order> getOrders() {
        return list;
    }

    public Order getOrder(int id) {
        return list.stream()
                .filter(f -> f.getId() == id).findFirst().orElse(null);
    }
}
