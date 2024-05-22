package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Основной класс для запуска приложения
 */
public class Supermarket {
    private List<Cashier> cashiers;

    public Supermarket(int numberOfCashiers) {
        cashiers = new ArrayList<>();
        for (int i = 1; i <= numberOfCashiers; i++) {
            cashiers.add(new Cashier(i));
        }
    }

    public void open() {
        for (Cashier cashier : cashiers) {
            cashier.start();
        }
    }

    public void addCustomer(Customer customer) {
        Random random = new Random();
        Cashier cashier = cashiers.get(random.nextInt(cashiers.size()));
        cashier.addCustomer(customer);
    }

    public static void main(String[] args) {
        ConfigLoader configLoader = new ConfigLoader();
        int numberOfCashiers = configLoader.loadConfig("config.xml");

        Supermarket supermarket = new Supermarket(numberOfCashiers);
        supermarket.open();

        for (int i = 0; i < 100; i++) {
            supermarket.addCustomer(new Customer(1000 + new Random().nextInt(4000)));
        }
    }
}
