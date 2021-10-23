import entity.Bill;
import entity.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import static java.lang.Thread.sleep;

class Multithreading extends Main implements Runnable {

    private final static int MAXIMUM_NO_OF_PRODUCTS = 4;

    public synchronized void run() {

        System.out.println("Thread " + Thread.currentThread().getId() + " is running");

        BlockingQueue<Product> boughtProducts = new LinkedBlockingQueue<>();
        Random rand = new Random();
        int nrOfProducts = rand.nextInt(MAXIMUM_NO_OF_PRODUCTS) + 1;

        for (int i = 0; i < nrOfProducts; i++) {
            int randomProductIndex = rand.nextInt(products.size());
            Product product = products.get(randomProductIndex);

            int noOfSaleQuantity = rand.nextInt(MAXIMUM_NO_OF_PRODUCTS) + 1;
            if (noOfSaleQuantity <= product.getQuantity()) {

                Product boughtProduct = new Product(product.getType(), noOfSaleQuantity, product.getUnitPrice());
                System.out.println("Bought -> " + boughtProduct);
                boughtProducts.add(boughtProduct);

                Product updatedProduct = new Product(product.getType(), product.getQuantity() - noOfSaleQuantity, product.getUnitPrice());
                products.set(randomProductIndex, updatedProduct);

                moneySpent += noOfSaleQuantity * product.getUnitPrice();
            }
        }

        Bill bill = new Bill(boughtProducts);
        bills.add(bill);

        System.out.println("Thread " + Thread.currentThread().getId() + " is finished\n");

        int timeToTime = rand.nextInt(MAXIMUM_NO_OF_PRODUCTS);
        if (timeToTime > 2) {
            check();
        }
    }
}

public class Main {

    private final static Integer THREADS = 100;
    private final static Integer TIME_TO_SLEEP = 3000;
    protected static List<Product> products = new ArrayList<>();
    protected static List<Bill> bills = new ArrayList<>();
    protected static int moneySpent = 0;

    public static void main(String[] args) throws InterruptedException {
        Product eggs = new Product("Egg", 60, 1);
        Product milk = new Product("Milk", 50, 5);
        Product chocolate = new Product("Chocolate", 120, 4);
        Product yogurt = new Product("Yogurt", 70, 3);

        products.add(eggs);
        products.add(milk);
        products.add(chocolate);
        products.add(yogurt);

        long startMilli = System.currentTimeMillis();
        Multithreading threads = new Multithreading();
        for (int i = 0; i < THREADS; i++) {
            Thread object = new Thread(threads);
            object.start();
        }
        long endMilli = System.currentTimeMillis() - startMilli;
        sleep(TIME_TO_SLEEP);
        System.out.println("Money spent: " + moneySpent);
        check();
        System.out.println("The time needed for the execution is " + endMilli + " milliseconds.");
    }

    protected static void check() {
        int totalPriceOfTheBills = 0;
        for (Bill b : bills) {
            totalPriceOfTheBills += b.getTotalPrice();
        }

        if (totalPriceOfTheBills == moneySpent) {
            System.out.println("Money are justified !\n");
        } else {
            System.err.println("There is something wrong!\n");
        }
    }
}
