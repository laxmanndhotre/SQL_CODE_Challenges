package mainModule;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import entities.Inventory;
import entities.Product;

public class MainClass {
    public static void main(String[] args) {
    	{
            Scanner scanner = new Scanner(System.in);
            Inventory inventory = null;

            while (true) {
                System.out.println("\n--- Inventory Management System ---");
                System.out.println("1. Create Product");
                System.out.println("2. View Product Details");
                System.out.println("3. Update Product Info");
                System.out.println("4. Add to Inventory");
                System.out.println("5. Remove from Inventory");
                System.out.println("6. View Inventory Value");
                System.out.println("7. Check Product Availability");
                System.out.println("8. List Low Stock Products");
                System.out.println("9. List Out of Stock Products");
                System.out.println("10. List All Products");
                System.out.println("11. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter Product ID: ");
                        int productId = scanner.nextInt();
                        scanner.nextLine();  // Consume newline

                        System.out.print("Enter Product Name: ");
                        String productName = scanner.nextLine();

                        System.out.print("Enter Product Description: ");
                        String productDescription = scanner.nextLine();

                        System.out.print("Enter Product Price: ");
                        double productPrice = scanner.nextDouble();

                        System.out.print("Enter Product Quantity in Stock: ");
                        int quantityInStock = scanner.nextInt();

                        Product product = new Product(productId, productName, productDescription, productPrice, quantityInStock);
                        inventory = new Inventory(1, product, quantityInStock, "2023-01-01 00:00:00");
                        System.out.println("Product created successfully.");
                        break;

                    case 2:
                        if (inventory != null) {
                            System.out.println("\nProduct Details:\n" + inventory.getProduct().getProductDetails());
                        } else {
                            System.out.println("No product found. Please create a product first.");
                        }
                        break;

                    case 3:
                        if (inventory != null) {
                            System.out.print("Enter new Product Price: ");
                            double newPrice = scanner.nextDouble();
                            scanner.nextLine();  // Consume newline

                            System.out.print("Enter new Product Description: ");
                            String newDescription = scanner.nextLine();

                            inventory.getProduct().updateProductInfo(newPrice, newDescription);
                            System.out.println("Product updated successfully.");
                        } else {
                            System.out.println("No product found. Please create a product first.");
                        }
                        break;

                    case 4:
                        if (inventory != null) {
                            System.out.print("Enter quantity to add: ");
                            int quantityToAdd = scanner.nextInt();
                            inventory.addToInventory(quantityToAdd);
                            System.out.println("Inventory updated successfully.");
                        } else {
                            System.out.println("No product found. Please create a product first.");
                        }
                        break;

                    case 5:
                        if (inventory != null) {
                            System.out.print("Enter quantity to remove: ");
                            int quantityToRemove = scanner.nextInt();
                            inventory.removeFromInventory(quantityToRemove);
							System.out.println("Inventory updated successfully.");
                        } else {
                            System.out.println("No product found. Please create a product first.");
                        }
                        break;

                    case 6:
                        if (inventory != null) {
                            System.out.println("\nInventory Value: " + inventory.getInventoryValue());
                        } else {
                            System.out.println("No product found. Please create a product first.");
                        }
                        break;

                    case 7:
                        if (inventory != null) {
                            System.out.print("Enter Product ID to check availability: ");
                            int checkProductId = scanner.nextInt();
                            scanner.nextLine();  // Consume newline

                            if (inventory.getProduct().getProductID() == checkProductId) {
                                System.out.print("Enter quantity to check: ");
                                int quantityToCheck = scanner.nextInt();
                                boolean isAvailable = inventory.isProductAvailable(quantityToCheck);
                                System.out.println("Product availability: " + (isAvailable ? "Available" : "Not Available"));
                            } else {
                                System.out.println("Product ID not found in inventory.");
                            }
                        } else {
                            System.out.println("No product found. Please create a product first.");
                        }
                        break;
                    case 8:
                        if (inventory != null) {
                            System.out.print("Enter low stock threshold: ");
                            int threshold = scanner.nextInt();
                            List<Product> lowStockProducts = inventory.listLowStockProducts(threshold);
                            System.out.println("Low Stock Products: " + (lowStockProducts.isEmpty() ? "None" : lowStockProducts.get(0).getProductDetails()));
                        } else {
                            System.out.println("No product found. Please create a product first.");
                        }
                        break;

                    case 9:
                        if (inventory != null) {
                            List<Product> outOfStockProducts = inventory.listOutOfStockProducts();
                            System.out.println("Out of Stock Products: " + (outOfStockProducts.isEmpty() ? "None" : outOfStockProducts.get(0).getProductDetails()));
                        } else {
                            System.out.println("No product found. Please create a product first.");
                        }
                        break;

                    case 10:
                        if (inventory != null) {
                            List<Product> allProducts = inventory.listAllProducts();
                            System.out.println("All Products in Inventory:");
                            for (Product p : allProducts) {
                                System.out.println(p.getProductDetails());
                            }
                        } else {
                            System.out.println("No product found. Please create a product first.");
                        }
                        break;

                    case 11:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);

                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
    	}
    }
}
