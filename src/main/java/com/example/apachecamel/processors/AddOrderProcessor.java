package com.example.apachecamel.processors;

import com.example.apachecamel.dtos.Order;
import com.example.apachecamel.services.OrderService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class AddOrderProcessor implements Processor {

    private OrderService orderService;

    public AddOrderProcessor(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void process(Exchange exchange) throws Exception {
        orderService.addOrder(exchange.getIn().getBody(Order.class));
    }
}
