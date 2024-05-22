package main.java;


/**
 * Класс для моделирования покупателя
 */
public class Customer {
    private static int idCounter = 0;
    private int id;
    private long serviceTime;

    public Customer(long serviceTime) {
        this.id = ++idCounter;
        this.serviceTime = serviceTime;
    }

    public int getId() {
        return id;
    }

    public long getServiceTime() {
        return serviceTime;
    }
}
