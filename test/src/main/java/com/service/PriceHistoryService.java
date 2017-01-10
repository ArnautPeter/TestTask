package com.services;


import com.repositories.PriceHistoryRepo;
import com.entities.PriceHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
//@Transactional
public class PriceHistoryService {

    @Autowired
    PriceHistoryRepo priceHistoryRepo;

    public List<PriceHistory> getAllPriceHistory() {
        return priceHistoryRepo.findAll();
    }

    public PriceHistory getById(int id) {
        return priceHistoryRepo.findOne(id);
    }

    public List<PriceHistory> getByProductId(int productId) {
        return priceHistoryRepo.findByProductId(productId);
    }

    public List<PriceHistory> getPriceBeforeDate(Date currentDate) {
        List<PriceHistory> list = priceHistoryRepo.findPriceBeforeDate(currentDate);
        return list;
    }

    public void deleteById(int id) {
        priceHistoryRepo.delete(id);
    }
}
