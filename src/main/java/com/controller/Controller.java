package com.controller;


import com.entity.DateAndPrice;
import com.entity.NamePrice;
import com.entity.Price;
import com.entity.PriceHistory;
import com.entity.Product;
import com.service.PriceHistoryService;
import com.service.ProductService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
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
    List<Price> getAll() {
        List<PriceHistory> priceHistories = priceHistoryService.getAllPriceHistory();
        List<Product> products = productService.getAllProducts();
        List<Price> prices = new ArrayList<>();
        Price price;
        for (PriceHistory priceHistory : priceHistories) {
            price = new Price();
            price.setPrice(priceHistory.getPrice());
            price.setDate(new DateTime(priceHistory.getValidDate()).toString());
            price.setName(products.get(priceHistory.getProductId() - 1).getName());
            prices.add(price);
        }

        return prices;
    }

    @RequestMapping(value = "/price/{name}", method = RequestMethod.GET)
    public
    @ResponseBody
    List<DateAndPrice> getProductHistoryByName(@PathVariable("name") String name) {
        List<Product> products = productService.getAllProducts();
        int productId = -1;
        for (Product product : products) {
            if (name.equals(product.getName())) {
                productId = product.getId();
                break;
            }
        }

        List<DateAndPrice> dateAndPrices = new ArrayList<>();
        List<PriceHistory> priceHistories = priceHistoryService.getByProductId(productId);
        DateAndPrice dateAndPrice;
        Date date;
        for (int i = 0; i < priceHistories.size(); i++) {
            dateAndPrice = new DateAndPrice();
            date = priceHistories.get(i).getValidDate();
            dateAndPrice.setDate(new DateTime(date).toString());
            dateAndPrice.setPrice(priceHistories.get(i).getPrice());
            dateAndPrices.add(dateAndPrice);
        }

        return dateAndPrices;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequestMapping(value = "/price/{date}")
    public
    @ResponseBody
    List<NamePrice> getReportOnDate(@PathVariable("date") String date) {
        List<PriceHistory> priceHistories = priceHistoryService.getPriceBeforeDate(date);
        List<NamePrice> namePrices = new ArrayList<>();
        NamePrice namePrice;
        for (PriceHistory priceHistory : priceHistories) {
            namePrice = new NamePrice();
            namePrice.setName(productService.getById(priceHistory.getProductId()).getName());
            namePrice.setPrice(priceHistory.getPrice());
            namePrices.add(namePrice);
        }

        return namePrices;
    }

    @RequestMapping("/priceHistory/delete/{id}")
    public
    @ResponseBody
    void deletePriceRecordById(@PathVariable("id") int id) {
        priceHistoryService.deleteById(id);
    }

    @RequestMapping(value = "/priceHistory/add", method = RequestMethod.POST)
    public
    @ResponseBody
    void addNewPrice(@RequestParam int price,@RequestParam Date date, @RequestParam int productId) {

        PriceHistory priceHistory = new PriceHistory();
        priceHistory.setPrice(price);
        priceHistory.setValidDate(date);
        priceHistory.setProductId(productId);
        priceHistoryService.add(priceHistory);
    }
}
