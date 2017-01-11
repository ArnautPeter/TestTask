package com.arnaut.services;

import com.arnaut.repositories.ProductRepo;
import com.arnaut.entities.Product;
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

    public void addProduct(Product product) {
        productRepo.save(product);
    }

    public Product getById(int id) {
        return productRepo.findOne(id);
    }
}
