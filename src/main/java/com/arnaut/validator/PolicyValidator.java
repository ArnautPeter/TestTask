package com.arnaut.validator;

import com.arnaut.entities.PriceHistory;
import com.arnaut.entities.Product;
import com.arnaut.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class PolicyValidator implements Validator {
//
//    @Autowired
//    ProductService productService;

    @Override
    public boolean supports(Class<?> clazz) {
        return PriceHistory.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors e) {

        ProductService productService = new ProductService();

        ValidationUtils.rejectIfEmpty(e, "productId", "productId.empty");

        PriceHistory p = (PriceHistory) target;
        Product product = new Product();
//        product.setId(-1);
//        try {
//            product = productService.getById(p.getProductId());
//        }catch (Exception e1) {
//
//        }
//        finally {
//            if (product.getId() == -1)
//                e.reject("productId", "wrong product id");
//        }
    }
}
