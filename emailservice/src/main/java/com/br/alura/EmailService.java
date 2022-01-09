package com.br.alura;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class EmailService {

    public static void main(String[] args) throws IOException {
        var myService = new EmailService();
        try(var service = new KafkaService<>(EmailService.class.getSimpleName(),
                "ECOMMERCE_SEND_EMAIL",
                myService::parse,
                String.class,
                Map.of())) {
            service.run();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void parse(ConsumerRecord<String, String> record) {
        System.out.println("------------------------------");
        System.out.println("Processing new email");
        System.out.println(record.key());
        System.out.println(record.value());
        System.out.println(record.partition());
        System.out.println(record.offset());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            // ignoring because its a simulation
            e.printStackTrace();
        }
        System.out.println("Order processed");
    }


}





