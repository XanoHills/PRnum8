package main.java;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Класс для моделирования кассира
 */
public class Cashier extends Thread {
    private int id;
    private BlockingQueue<Customer> queue;

    public Cashier(int id) {
        this.id = id;
        this.queue = new LinkedBlockingQueue<>();
    }

    public void addCustomer(Customer customer) {
        queue.add(customer);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Customer customer = queue.take();
                System.out.println("Cashier " + id + " is serving customer " + customer.getId());
                Thread.sleep(customer.getServiceTime());
                System.out.println("Cashier " + id + " finished serving customer " + customer.getId());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Cashier " + id + " was interrupted.");
                break;
            }
        }
    }

    public int getCashierId() {
        return id;
    }
}
