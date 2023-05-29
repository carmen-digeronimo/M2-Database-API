package com.techelevator.dao;

import com.techelevator.model.CartItem;
import com.techelevator.model.User;

import java.util.List;

public interface CartItemDao {

    public List<CartItem> getCart(int userId);

    public CartItem addItem(CartItem item);

    public void deleteItem(int cartItemId);

    public void deleteCart(int userId);

}
