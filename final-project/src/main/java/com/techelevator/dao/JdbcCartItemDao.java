package com.techelevator.dao;

import com.techelevator.model.CartItem;
import com.techelevator.model.Product;
import com.techelevator.model.TaxRate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcCartItemDao implements CartItemDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcCartItemDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<CartItem> getCart(int userId) {
        List<CartItem> cart = new ArrayList<>();
        String sql = "SELECT cart_item.cart_item_id, cart_item.user_id, product.name, product.price, cart_item.quantity, (product.price*cart_item.quantity) AS product_cost FROM cart_item" +
                " JOIN product ON cart_item.product_id = product.product_id WHERE user_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while(results.next()) {
            CartItem item = mapRowToCardItem(results);
            cart.add(item);
        }
        return cart;
    }

    public double getTotal(int userId) {
        double total = 0;
        String sql = "SELECT cart_item.user_id, SUM(product.price*cart_item.quantity) AS cart_total, FROM cart_item" +
                " JOIN product ON cart_item.product_id = product.product_id WHERE user_id = ?";
        total = jdbcTemplate.queryForObject(sql, Double.class, userId);

        return total;
    }

    @Override
    public CartItem addItem(CartItem item) {
        String sql = "INSERT INTO cart_item (user_id, product_id, quantity) VALUES (?,?,?) RETURNING cart_item_id";
        Integer cartItemId = jdbcTemplate.update(sql, Integer.class, item.getUserId(), item.getProductId(), item.getQuantity());
        item.setId(cartItemId);
        return item;
    }

    @Override
    public void deleteItem(int cartItemId) {
        String sql = "DELETE FROM cart_item WHERE cart_item_id = ?";
        jdbcTemplate.update(sql, cartItemId);
    }

    @Override
    public void deleteCart(int userId) {
        String sql = "DELETE FROM cart_item WHERE user_id = ?";
        jdbcTemplate.update(sql, userId);
    }

    private CartItem mapRowToCardItem(SqlRowSet result) {
        CartItem cartItem = new CartItem();
        TaxRate tax = new TaxRate();
        cartItem.setId(result.getInt("cart_item_id"));
        cartItem.setUserId(result.getInt("user_id"));
        cartItem.setProductId(result.getInt("product_id"));
        cartItem.setQuantity(result.getInt("quantity"));
        cartItem.setProductCost(result.getDouble("product_cost"));
        return cartItem;
    }
}
