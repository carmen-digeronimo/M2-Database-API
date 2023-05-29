package com.techelevator.dao;

import com.techelevator.model.Product;

import java.util.List;

public interface ProductDao {

    public List<Product> getAllProducts();

    public List<Product> getProductByName(String productName);

    public List<Product> getProductBySku(String productSku);

    public Product getProduct(int productId);
}
