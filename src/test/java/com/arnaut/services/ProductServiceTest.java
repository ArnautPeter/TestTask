package com.arnaut.services;


import com.arnaut.entities.Product;
import com.arnaut.repositories.ProductRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    @InjectMocks
    ProductService productService = new ProductService();

    @Mock
    ProductRepo productRepo;

    @Mock
    Product product;

    @Before
    public void init() {
        int productId = 5;
        String name = "Soda";
        product = new Product();
        product.setId(productId);
        product.setName(name);
    }

    @Test
    public void getAllProductsTest() {

        when(productRepo.findAll()).thenReturn(Arrays.asList(product));
        assertThat(productService.getAllProducts().get(0)).hasNoNullFieldsOrProperties();
    }

    @Test
    public void getByIdTest() {
        when(productRepo.findOne(5)).thenReturn(product);
        assertThat(productService.getById(5)).hasNoNullFieldsOrProperties();
    }
}
