package com.example.apachecamel.resources;

import com.example.apachecamel.dtos.Order;
import com.example.apachecamel.processors.AddOrderProcessor;
import com.example.apachecamel.services.OrderService;
import org.apache.camel.BeanInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class ApplicationResource extends RouteBuilder {

    @BeanInject
    private AddOrderProcessor addOrderProcessor;


    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json);

        rest()
                .get("/hello-world")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .route()
                .setBody(constant("Hello, welcome to Apache Camel"))
                .endRest();


        rest()
                .get("/get-orders")
                .description("Gets all the orders")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .route()
                .routeId("orders-api")
                .bean(OrderService.class, "getOrders")
                .endRest()

                .get("/orders/{id}")
                .description("Gets an order item")
                .route()
                .routeId("order-api")
                .bean(OrderService.class, "getOrder(${header.id})")
                .endRest()


                .post("/add-order")
                .description("Adds an order to the database")
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .type(Order.class)
                .outType(Order.class)
                .route()
                .process(addOrderProcessor)
                .endRest();

    }
}
