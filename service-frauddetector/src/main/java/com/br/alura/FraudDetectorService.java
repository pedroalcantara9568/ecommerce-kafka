package com.br.alura;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.io.IOException;
import java.util.HashMap;

public class FraudDetectorService {
    public static void main(String[] args) {
        var fraudService = new FraudDetectorService();
        try (var service = new KafkaService
                (EmailService.class.getSimpleName(),
                        "ECOMMERCE_NEW_ORDER",
                        fraudService::parse, Order.class,
                        new HashMap<String, String>())) {
            service.run();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void parse(ConsumerRecord<String, Order> record) throws InterruptedException {
        System.out.println("------------------------------");
        System.out.println("Processing new order");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
        Thread.sleep(500);
        System.out.println("Order processed");
    }


}
