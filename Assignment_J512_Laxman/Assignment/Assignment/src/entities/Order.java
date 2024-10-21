package entities;

public class Order {
    private int orderID;
    private Customer customer;
    private String orderDate;
    private double totalAmount;

    public Order(int orderID, Customer customer, String orderDate, double totalAmount) {
        this.orderID = orderID;
        this.customer = customer;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }
    
 
    public int getOrderID() {
        return orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

   
    public void setTotalAmount(double totalAmount) {
        if (totalAmount < 0) {
            throw new IllegalArgumentException("Total amount cannot be negative");
        }
        this.totalAmount = totalAmount;
    }
    

    public double calculateTotalAmount() {
        // Implement your logic here
        return 0.0;
    }

    public String getOrderDetails() {
        // Implement your logic here
        return "";
    }

    public void updateOrderStatus(String status) {
        // Implement your logic here
    }

    public void cancelOrder() {
        // Implement your logic here
    }
}
