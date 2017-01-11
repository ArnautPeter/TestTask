package com.arnaut.controllers;

import com.arnaut.entities.NamePrice;
import com.arnaut.entities.PriceHistory;
import com.arnaut.entities.Product;
import com.arnaut.services.PriceHistoryService;
import com.arnaut.services.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ControllerTest {

    @InjectMocks
    Controller controller;

    @Mock
    ProductService productService;

    @Mock
    PriceHistoryService priceHistoryService;

    @Mock
    Product product;

    @Mock
    NamePrice namePrice;

    @Mock
    PriceHistory priceHistory;

    @Before
    public void init() {
        product.setId(1);
        product.setName("banana");
        priceHistory.setProductId(1);
    }


    @Test
    public void getAllProductsTest() {
        when(productService.getAllProducts()).thenReturn(Arrays.asList(new Product()));
        assertThat(controller.getAllProducts().size(), is(1));
    }

    @Test
    public void addProductTest() {
        String name = "Test product";
        ResponseEntity<String> result = controller.addProduct(name);
        assertTrue(result.toString().contains(name + " has been added"));
    }

    @Test
    public void getProductHistoryByNameTest() {
        controller.getProductHistoryByName("test");
        verify(priceHistoryService, times(1)).getByProductId(-1);
    }

    @Test
    public void getReportOnDateTest() {
        controller.getReportOnDate("2017");
        verify(priceHistoryService, times(1)).getPriceBeforeDate("2017");
    }

    @Test
    public void deletePriceRecordByIdNotFountTest() {

        int id = 5;
        when(priceHistoryService.getById(id)).thenReturn(null);
        ResponseEntity<String> result = controller.deletePriceRecordById(id);
        assertThat(result.toString(), containsString("Record with id " + id + " not found"));
    }

    @Test
    public void deletePriceRecordByTest() {

        int id = 5;
        when(priceHistoryService.getById(id)).thenReturn(new PriceHistory());
        ResponseEntity<String> result = controller.deletePriceRecordById(id);
        assertThat(result.toString(), containsString("Deleted"));
    }

    @Test
    public void addNewPriceTest() {
        when(priceHistoryService.countProducts(0)).thenReturn(1);
        ResponseEntity<String> result = controller.addNewPrice(new PriceHistory());
        assertThat(result.toString(), containsString("Added"));
    }

    @Test
    public void addNewPriceWrongProductId() {
        when(priceHistoryService.countProducts(0)).thenReturn(0);
        ResponseEntity<String> result = controller.addNewPrice(new PriceHistory());
        assertThat(result.toString(), containsString("Wrong productId"));
    }

    @Test
    public void editPriceNotFoundTest() {
        int id = 5;
        PriceHistory priceHistory = new PriceHistory();
        priceHistory.setId(5);
        when(priceHistoryService.getById(id)).thenReturn(null);
        ResponseEntity<String> result = controller.editPrice(priceHistory);
        assertThat(result.toString(), containsString("Record with id " + id + " not found"));
    }

    @Test
    public void editPriceWrongProductIdTest() {
        int id = 5;
        PriceHistory priceHistory = new PriceHistory();
        when(priceHistoryService.getById(id)).thenReturn(priceHistory);
        priceHistory.setId(5);
        ResponseEntity<String> result = controller.editPrice(priceHistory);
        assertThat(result.toString(), containsString("Wrong productId"));
    }

    @Test
    public void editPriceTest() {
        int id = 5;
        PriceHistory priceHistory = new PriceHistory();
        when(priceHistoryService.getById(id)).thenReturn(priceHistory);
        priceHistory.setId(5);
        when(priceHistoryService.countProducts(0)).thenReturn(1);
        ResponseEntity<String> result = controller.editPrice(priceHistory);
        assertThat(result.toString(), containsString("Changed"));
    }
}
