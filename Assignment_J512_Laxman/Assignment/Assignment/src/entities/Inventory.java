package entities;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private int inventoryID;
    private Product product;
    private int quantityInStock;
    private String lastStockUpdate;

    public Inventory(int inventoryID, Product product, int quantityInStock, String lastStockUpdate) {
        this.inventoryID = inventoryID;
        this.product = product;
        this.quantityInStock = quantityInStock;
        this.lastStockUpdate = lastStockUpdate;
    }

    public Product getProduct() {
        return this.product;
    }

    public int getQuantityInStock() {
        return this.quantityInStock;
    }

    public void addToInventory(int quantity) {
        if (quantity > 0) {
            this.quantityInStock += quantity;
            updateLastStockUpdate();
        }
    }

    public void removeFromInventory(int quantity) {
        if (quantity > 0 && this.quantityInStock >= quantity) {
            this.quantityInStock -= quantity;
            updateLastStockUpdate();
        }
    }

    public void updateStockQuantity(int newQuantity) {
        if (newQuantity >= 0) {
            this.quantityInStock = newQuantity;
            updateLastStockUpdate();
        }
    }

    public boolean isProductAvailable(int quantityToCheck) {
        return this.quantityInStock >= quantityToCheck;
    }

    public double getInventoryValue() {
        return this.product.getPrice() * this.quantityInStock;
    }

    public List<Product> listLowStockProducts(int threshold) {
        List<Product> lowStockProducts = new ArrayList<>();
        if (this.quantityInStock <= threshold) {
            lowStockProducts.add(this.product);
        }
        return lowStockProducts;
    }

    public List<Product> listOutOfStockProducts() {
        List<Product> outOfStockProducts = new ArrayList<>();
        if (this.quantityInStock == 0) {
            outOfStockProducts.add(this.product);
        }
        return outOfStockProducts;
    }

    public List<Product> listAllProducts() {
        List<Product> allProducts = new ArrayList<>();
        allProducts.add(this.product);
        return allProducts;
    }

    private void updateLastStockUpdate() {
        this.lastStockUpdate = LocalDateTime.now().toString();
    }

	public int getInventoryID() {
		return inventoryID;
	}

	public void setInventoryID(int inventoryID) {
		this.inventoryID = inventoryID;
	}

	public String getLastStockUpdate() {
		return lastStockUpdate;
	}

	public void setLastStockUpdate(String lastStockUpdate) {
		this.lastStockUpdate = lastStockUpdate;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}
}
