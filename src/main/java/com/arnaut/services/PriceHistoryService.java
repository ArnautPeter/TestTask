package com.arnaut.services;


import com.arnaut.entities.PriceHistory;
import com.arnaut.repositories.PriceHistoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
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


    public List<PriceHistory> getPriceBeforeDate(String currentDate) {
        List<PriceHistory> list = priceHistoryRepo.findPriceBeforeDate(currentDate);
        Map<Integer, PriceHistory> result = new HashMap<>();
        for (PriceHistory priceHistory : list) {
            if (result.containsKey(priceHistory.getProductId())) {
                LocalDateTime date = result.get(priceHistory.getProductId()).getValidDate();
                if(date.isAfter(priceHistory.getValidDate()))
                    continue;
            }
            result.put(priceHistory.getProductId(), priceHistory);
        }
        return new ArrayList<>(result.values());
    }


    public void add(PriceHistory priceHistory) {
        priceHistoryRepo.save(priceHistory);
    }

    public void deleteById(int id) {
        priceHistoryRepo.delete(id);
    }

    public int countProducts(Integer productId) {
        return priceHistoryRepo.countValues(productId);
    }
}
