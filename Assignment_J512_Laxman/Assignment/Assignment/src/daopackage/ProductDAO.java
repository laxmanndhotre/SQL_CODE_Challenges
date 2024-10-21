package daopackage;


import entities.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {
    void createProduct(Product product) throws SQLException;
    Product getProductById(int productId) throws SQLException;
    List<Product> getAllProducts() throws SQLException;
    boolean updateProduct(Product product) throws SQLException;
    boolean deleteProduct(int productId) throws SQLException;
}
