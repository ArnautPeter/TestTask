package com.arnaut.controllers;


import com.arnaut.entities.NamePrice;
import com.arnaut.entities.PriceHistory;
import com.arnaut.services.ProductService;
import com.arnaut.entities.Product;
import com.arnaut.services.PriceHistoryService;
import com.arnaut.validator.PolicyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    PriceHistoryService priceHistoryService;

    @Autowired
    ProductService productService;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(new PolicyValidator());
    }


    @RequestMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @RequestMapping("/product/add/{name}")
    public ResponseEntity<String> addProduct(@PathVariable("name") String name) {
        Product product = new Product();
        product.setName(name);
        productService.addProduct(product);
        return ResponseEntity.ok(name + " has been added");
    }

    @RequestMapping("/price")
    public List<PriceHistory> getAllPriceHistory() {
        List<PriceHistory> priceHistories = priceHistoryService.getAllPriceHistory();
        return priceHistories;
    }

    @RequestMapping(value = "/price/{name}", method = RequestMethod.GET)
    public List<PriceHistory> getProductHistoryByName(@PathVariable("name") String name) {
        List<Product> products = productService.getAllProducts();
        int productId = -1;
        for (Product product : products) {
            if (name.equals(product.getName())) {
                productId = product.getId();
                break;
            }
        }

        List<PriceHistory> priceHistories = priceHistoryService.getByProductId(productId);
        return priceHistories;
    }

    @RequestMapping(value = "/price/report/{date}", method = RequestMethod.GET)
    public List<NamePrice> getReportOnDate(@PathVariable("date") String date) {
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
    public ResponseEntity<String> deletePriceRecordById(@PathVariable("id") int id) {
        if (priceHistoryService.getById(id) != null) {
            priceHistoryService.deleteById(id);
            return ResponseEntity.ok("Deleted");
        }
        return ResponseEntity.ok("Record with id " + id + " not found");
    }

    @RequestMapping(value = "/priceHistory/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addNewPrice(@Valid @RequestBody PriceHistory priceHistory) {
        priceHistoryService.add(priceHistory);

        return ResponseEntity.ok("Added");
    }

    @RequestMapping(value = "/priceHistory/edit", method = RequestMethod.POST)
    @Valid
    @ResponseBody
    public ResponseEntity<String> editPrice(@Valid @RequestBody PriceHistory priceHistory) {

        PriceHistory tempPriceHistory = priceHistoryService.getById(priceHistory.getId());
        if (tempPriceHistory == null) {
            return ResponseEntity.ok("Record with id " + priceHistory.getId() + " not found");
        }

        tempPriceHistory.setPrice(priceHistory.getPrice());
        tempPriceHistory.setValidDate(priceHistory.getValidDate());
        tempPriceHistory.setProductId(priceHistory.getProductId());
        priceHistoryService.add(tempPriceHistory);

        return ResponseEntity.ok("Changed");
    }
}
