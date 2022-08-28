package io.github.jihch;

public class App {

    public static void main(String[] args) {

        RabbitMQClient instance = new RabbitMQClient.Builder().setHost("192.168.31.201").setMode(2).setExchange("test" +
                "-exchange").build();

        instance.sendMessage("test");

    }

}
