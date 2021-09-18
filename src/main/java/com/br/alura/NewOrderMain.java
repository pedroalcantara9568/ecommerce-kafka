package com.br.alura;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
       var orderDispatcher = new KafkaDispatcher<Order>();
       var emailDispatcher = new KafkaDispatcher<>();
        var userId = UUID.randomUUID().toString();
        var orderId = UUID.randomUUID().toString();
        var amount = new BigDecimal(Math.random() * 5000 + 1);
        var order = new Order(userId, orderId, amount);
       // var value =  key  + "499455, 877322";
        orderDispatcher.send("ECOMMERCE_NEW_ORDER", userId, order);
        var email = "Welcome! We are processing your order";
        emailDispatcher.send("ECOMMERCE_SEND_EMAIL", userId, email);
    }



}
