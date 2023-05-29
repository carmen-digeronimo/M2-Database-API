package com.techelevator.dao;

import com.techelevator.model.Product;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcProductDao implements ProductDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcProductDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            Product product = mapRowToProduct(results);
            products.add(product);
        }
        return products;
    }

    @Override
    public List<Product> getProductByName(String productName) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE name = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, productName);
        while(results.next()) {
            Product product = mapRowToProduct(results);
            products.add(product);
        }
        return products;
    }

    @Override
    public List<Product> getProductBySku(String productSku) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE product_sku = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, productSku);
        while(results.next()) {
            Product product = mapRowToProduct(results);
            products.add(product);
        }
        return products;
    }

    @Override
    public Product getProduct(int productId) {
        Product product = null;
        String sql = "SELECT * FROM product WHERE product_id = ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, productId);
        if(results.next()) {
             product = mapRowToProduct(results);
        }
        return product;
    }

    private Product mapRowToProduct(SqlRowSet result) {
        Product product = new Product();
        product.setId(result.getInt("product_id"));
        product.setSku(result.getString("product_sku"));
        product.setName(result.getString("name"));
        product.setDescription(result.getString("description"));
        product.setPrice(result.getDouble("price"));
        product.setImageName(result.getString("image_name"));
        return product;
    }

}
