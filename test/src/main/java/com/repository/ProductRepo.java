package com.repositories;

import com.entities.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface ProductRepo extends CrudRepository<Product, Integer>{

    @Override
    Product save(Product product);

    @Override
    Product findOne(Integer integer);

    @Override
    List<Product> findAll();

    @Override
    void delete(Integer integer);
}
