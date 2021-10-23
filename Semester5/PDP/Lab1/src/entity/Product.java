package entity;

public class Product {
    private final String type;
    private final int quantity;
    private final int unitPrice;

    public Product(String type, int quantity, int unitPrice) {
        this.type = type;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Product{" +
                "type='" + type + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
