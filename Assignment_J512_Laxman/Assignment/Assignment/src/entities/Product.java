package entities;
public class Product {

  private int productID;
  private String productName;
  private String description;
  private double price;
  private int quantityInStock; 
  
  



  public Product(int productID, String productName, String description, double price, int quantityInStock) {
    this.productID = productID;
    this.productName = productName;
    this.description = description;
    this.price = price;
    this.quantityInStock = quantityInStock;
  }


  public String getProductDetails() {
    StringBuilder details = new StringBuilder();
    details.append("Product ID: ").append(productID).append("\n");
    details.append("Name: ").append(productName).append("\n");
    details.append("Description: ").append(description).append("\n");
    details.append("Price: ").append(price).append("\n");
    return details.toString();
  }
  
  public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

  public void updateProductInfo(double price, String description) {
	  
  }

  public boolean isProductInStock() {
    return quantityInStock > 0;
  }

  public void removeFromStock(int quantity) {
    if (quantity > 0 && quantity <= this.quantityInStock) {
      this.quantityInStock -= quantity;
    } else {
      throw new IllegalArgumentException("Insufficient stock");
    }
  }

  public void addToStock(int quantity) {
    this.quantityInStock += quantity;
  }



}