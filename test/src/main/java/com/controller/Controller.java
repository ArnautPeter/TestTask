package com.controllers;


import com.entities.PriceHistory;
import com.entities.Product;
import com.services.PriceHistoryService;
import com.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    PriceHistoryService priceHistoryService;

    @Autowired
    ProductService productService;

    @RequestMapping("/products")
    public
    @ResponseBody
    List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @RequestMapping("/product/add/{name}")
    public
    @ResponseBody
    void addProduct(@PathVariable("name") String name) {
        Product product = new Product();
        product.setName(name);
        productService.addProduct(product);
    }

    @RequestMapping("/price")
    public
    @ResponseBody
    List<PriceHistory> getPriceHistory() {
        return priceHistoryService.getAllPriceHistory();
    }

    @RequestMapping("/price/{name}")
    public
    @ResponseBody
    List<PriceHistory> getProductHistoryByName(@PathVariable("name") String name) {
        List<Product> products = productService.getAllProducts();
        int productId = -1;
        for (Product product : products) {
            if (name.equals(product.getName())) {
                productId = product.getId();
                break;
            }
        }
        return priceHistoryService.getByProductId(productId);
    }

    @RequestMapping("/price/delete/{id}")
    public
    @ResponseBody
    void deletePriceRecordById(@PathVariable("id") int id) {
        priceHistoryService.deleteById(id);
    }


    @RequestMapping("/test/{id}")
    public
    @ResponseBody
    Object test(@PathVariable("id") int id) {
        String test = "1970";
        if (priceHistoryService.getById(id).getValidDate() != null)
            String.format("%tY", priceHistoryService.getById(id).getValidDate());
        //return priceHistoryService.getById(id);
        return test;
    }
}
