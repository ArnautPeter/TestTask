package com.repository;

import com.entity.DateAndPrice;
import com.entity.Price;
import com.entity.PriceHistory;
import com.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface PriceHistoryRepo extends CrudRepository<PriceHistory, Integer> {

    @Query("FROM PriceHistory c WHERE product_id = :productId")
    List<PriceHistory> findByProductId(@Param("productId") int productId);

    @Query("FROM PriceHistory c WHERE validdata <= :currentDate")
    List<PriceHistory> findPriceBeforeDate(@Param("currentDate")String currentDate);

    @Override
    List<PriceHistory> findAll();

    @Override
    PriceHistory findOne(Integer id);

    @Override
    PriceHistory save(PriceHistory priceHistory);

    @Override
    void delete(Integer id);
}
