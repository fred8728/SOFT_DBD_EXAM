package Model;

public class OrderDetails {
    private int item_id;
    private int price;
    private int amount;
    private int order_id;

    public OrderDetails(int item_id, int price, int amount) {
        this.item_id = item_id;
        this.price = price;
        this.amount = amount;
        this.order_id = order_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public int getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }


    @Override
    public String toString() {
        return "OrderDetails{" +
                "item_id=" + item_id +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }
}
