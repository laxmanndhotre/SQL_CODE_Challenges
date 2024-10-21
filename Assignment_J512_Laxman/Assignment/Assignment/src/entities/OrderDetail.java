package entities;


public class OrderDetail {
    private int orderDetailID;
    private Order order;
    private Product product;
    private int quantity;

    public OrderDetail(int orderDetailID, Order order, Product product, int quantity) {
        this.orderDetailID = orderDetailID;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }
    
    public int getOrderDetailID() {
        return orderDetailID;
    }

    public Order getOrder() {
        return order;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        this.quantity = quantity;
    }

    public double calculateSubtotal() {
        return 0.0;
    }

    public String getOrderDetailInfo() {
        return "";
    }

    public void updateQuantity(int quantity) {
    }

    public void addDiscount(double discount) {
    }
}

