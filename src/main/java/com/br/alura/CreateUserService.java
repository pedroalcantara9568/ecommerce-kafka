package com.br.alura;

import com.br.alura.KafkaDispatcher;
import com.br.alura.KafkaService;
import com.br.alura.Order;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class CreateUserService {

    CreateUserService()
    {

    }

    public static void main(String[] args) {
        var userService = new CreateUserService();
        try (var service = new KafkaService<>(CreateUserService.class.getSimpleName(),
                "ECOMMERCE_NEW_ORDER",
                userService::parse,
                Order.class,
                Map.of())) {
            service.run();
        } catch (IOException | ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void parse(ConsumerRecord<String, Order> record) throws ExecutionException, InterruptedException {
        System.out.println("------------------------------");
        System.out.println("Processing new order, checking for user");
        System.out.println(record.key());
        var order = record.value();
        System.out.println(order);
        System.out.println(record.partition());
        System.out.println(record.offset());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // ignoring because its a simulation
            e.printStackTrace();
        }

    }

}