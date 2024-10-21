package entities;

public class Customer {
    private int customerID;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;

    public Customer(int customerID, String firstName, String lastName, String email, String phone, String address) {
        this.customerID = customerID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public int calculateTotalOrders() {
        // Implement your logic here
        return 0;
    }

    public String getCustomerDetails() {
        // Implement your logic here
        return "";
    }

    public void updateCustomerInfo(String email, String phone, String address) {
        // Implement your logic here
    }
}

