package com.arnaut.services;

import com.arnaut.entities.PriceHistory;
import com.arnaut.entities.Product;
import com.arnaut.repositories.PriceHistoryRepo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PriceHistoryServiceTest {


    @InjectMocks
    PriceHistoryService priceHistoryService;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    PriceHistoryRepo priceHistoryRepo;

    @Mock
    PriceHistory priceHistory;

    @Mock
    Product product;


    @Before
    public void init() {
        priceHistory = new PriceHistory();
        priceHistory.setId(1);
        priceHistory.setProductId(1);

        product = new Product();
        product.setId(1);
    }

    @Test
    public void getAllPriceHistoryTest() {
        when(priceHistoryRepo.findAll()).thenReturn(Arrays.asList(priceHistory));
        assertThat(priceHistoryService.getAllPriceHistory().get(0))
                .hasFieldOrProperty("validDate");
    }

    @Test
    public void getByIdTest() {
        when(priceHistoryRepo.findOne(priceHistory.getId())).thenReturn(priceHistory);
        assertThat(priceHistoryService.getById(1)).hasFieldOrProperty("validDate");
    }

    @Test
    public void getByProductIdTest() {
        when(priceHistoryRepo.findByProductId(product.getId()))
                .thenReturn(Arrays.asList(priceHistory));
        assertThat(priceHistoryService.getByProductId(1).get(0).getProductId(), is(1));
    }

    @Test
    public void getPriceBeforeDateTest() {
        when(priceHistoryRepo.findPriceBeforeDate("2014"))
                .thenReturn(Arrays.asList(priceHistory));
        assertThat(priceHistoryService.getPriceBeforeDate("2014").size(), is(1));
    }
}
