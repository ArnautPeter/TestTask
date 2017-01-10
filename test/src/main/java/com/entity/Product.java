package com.entities;


import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "product")
@Transactional
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;


//    private List<PriceHistory> priceHistories;

//    @OneToMany(mappedBy = "productE")
//    public List<PriceHistory> getPriceHistories() {
//        return priceHistories;
//    }
//
//    public void setPriceHistories(List<PriceHistory> priceHistories) {
//        this.priceHistories = priceHistories;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Product product = (Product) o;
//
//        if (id != product.id) return false;
//        if (name != null ? !name.equals(product.name) : product.name != null) return false;
//        return priceHistories != null ? priceHistories.equals(product.priceHistories) : product.priceHistories == null;
//
//    }
//
//    @Override
//    public int hashCode() {
//        int result = id;
//        result = 31 * result + (name != null ? name.hashCode() : 0);
//        result = 31 * result + (priceHistories != null ? priceHistories.hashCode() : 0);
//        return result;
//    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                //", priceHistories=" + priceHistories +
                '}';
    }
}
