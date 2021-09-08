package com.br.alura;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
       var dispatcher = new KafkaDispatcher();
        var key = UUID.randomUUID().toString();
        var value =  key  + "499455, 877322";
        dispatcher.send("ECOMMERCE_NEW_ORDER", key, value);
        var email = "Welcome! We are processing your order";
        dispatcher.send("ECOMMERCE_SEND_EMAIL", key, email);
    }



}