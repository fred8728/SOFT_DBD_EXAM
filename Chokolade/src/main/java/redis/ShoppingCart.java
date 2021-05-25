package redis;

public class ShoppingCart {

    public String productid;
    public String orderid;
    public String userid;
    public int price;
    public boolean status;
    public int amount;

    public ShoppingCart(String productid, String orderid, String userid, int price, boolean status, int amount) {
        this.productid = productid;
        this.orderid = orderid;
        this.userid = userid;
        this.price = price;
        this.status = status;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "productid=" + productid +
                "orderid='" + orderid + '\'' +
                ", userid='" + userid + '\'' +
                ", price=" + price +
                ", status=" + status +
                ", amount=" + amount +
                '}';
    }
}
