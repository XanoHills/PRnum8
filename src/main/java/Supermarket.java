package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
        Scanner scanner = new Scanner(System.in);
        int numberOfCashiers = 0;

        System.out.println("Enter the number of cashiers (natural number):");
        while (numberOfCashiers <= 0) {
            if (scanner.hasNextInt()) {
                numberOfCashiers = scanner.nextInt();
                if (numberOfCashiers <= 0) {
                    System.out.println("Please enter a natural number greater than 0.");
                }
            } else {
                System.out.println("Invalid input. Please enter a natural number.");
                scanner.next(); // Clear the invalid input
            }
        }

        // Сохранение конфигурации в XML
        configLoader.saveConfig("config.xml", numberOfCashiers);

        numberOfCashiers = configLoader.loadConfig("config.xml");

        Supermarket supermarket = new Supermarket(numberOfCashiers);
        supermarket.open();

        for (int i = 0; i < 100; i++) {
            supermarket.addCustomer(new Customer(1000 + new Random().nextInt(4000)));
        }
    }
}
