package entity;

import java.util.concurrent.BlockingQueue;

public class Bill {

    private final BlockingQueue<Product> products;
    private int totalPrice;

    public Bill(BlockingQueue<Product> products) {
        this.products = products;
        for (Product product : products) {
            int quantity = product.getQuantity();
            int price = product.getUnitPrice();
            this.totalPrice += quantity * price;
        }
    }

    public BlockingQueue<Product> getProducts() {
        return products;
    }

    public int getTotalPrice() {
        return totalPrice;
    }
}
