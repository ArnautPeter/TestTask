package com.arnaut.controllers;

import com.arnaut.entities.Product;
import com.arnaut.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(JUnit4.class)
public class ControllerTest {

    @InjectMocks
    Controller controller;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    ProductService productService;

    @Mock
    Product product;

    @Before
    public void init() {
        productService = mock(ProductService.class);
    }


    @Test
    public void getProductHistoryByNameTest() {
        Product product = new Product();
        product.setId(1);
        product.setName("banana");
        List<Product> products = new ArrayList<>();
        products.add(product);
        when(productService.getAllProducts()).thenReturn(products);

        //verify(productService, atLeastOnce()).getAllProducts();
    }
}
