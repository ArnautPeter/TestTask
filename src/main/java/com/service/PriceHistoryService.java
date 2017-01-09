package com.service;


import com.repository.PriceHistoryRepo;
import com.entity.PriceHistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
                Date date = result.get(priceHistory.getProductId()).getValidDate();
                if(date.after(priceHistory.getValidDate()))
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
}
