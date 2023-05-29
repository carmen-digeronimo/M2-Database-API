package com.techelevator.controller;

import com.techelevator.dao.JdbcCartItemDao;
import com.techelevator.dao.JdbcProductDao;
import com.techelevator.model.CartItem;
import com.techelevator.model.Product;
import com.techelevator.services.RestTaxRateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private JdbcCartItemDao cartItemDao;
    private RestTaxRateService taxRateService;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public List<CartItem> getCart(@PathVariable int userId) {
        return cartItemDao.getCart(userId);
    }

    @RequestMapping(path = "/items", method = RequestMethod.POST)
    public CartItem create(@RequestBody CartItem cartItem) {
        if(cartItem != null) {
            return cartItemDao.addItem(cartItem);
        }
        return null;
    }

    @RequestMapping(path = "/items/{cart_item_id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable int itemId) {
        cartItemDao.deleteItem(itemId);
    }

    @RequestMapping(path = "/items/{user_id}", method = RequestMethod.DELETE)
    public void deleteCart(@PathVariable int userId) {
        cartItemDao.deleteCart(userId);
    }








}
