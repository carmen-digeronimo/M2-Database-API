package com.techelevator.controller;


import com.techelevator.dao.JdbcCartItemDao;
import com.techelevator.dao.JdbcProductDao;
import com.techelevator.model.Product;
import com.techelevator.services.RestTaxRateService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private JdbcProductDao productDao;

    @RequestMapping(path = "", method = RequestMethod.GET)
    public List<Product> list() {
        return productDao.getAllProducts();
    }

    @RequestMapping(path = "/name={product_name}", method = RequestMethod.GET)
    public List<Product> listByName(@PathVariable String name) {
        return productDao.getProductByName(name);
    }

    @RequestMapping(path = "?sku={product_sku}", method = RequestMethod.GET)
    public List<Product> listBySkuName(@PathVariable String sku) {
        return productDao.getProductBySku(sku);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable int id) {
        Product product = productDao.getProduct(id);
        if (product != null) {
            return product;
        }
        return null;
    }

}
