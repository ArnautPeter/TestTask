package com.service;

import com.entity.Product;
import com.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public void addProduct(Product name) {
        productRepo.save(name);
    }

    public Product getById(int id) {
        return productRepo.findOne(id);
    }
}
