package com.entities;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "price_history")
public class PriceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int price;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="validdata")
    private Date validDate;

    private int productId;

//    private Product productE;

//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    public Product getProduct() {
//        return productE;
//    }

//    public void setProduct(Product product) {
//        this.productE = product;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getValidDate() {
        return validDate;
    }

    public void setValidDate(Date validDate) {
        this.validDate = validDate;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }


    @Override
    public String toString() {
        return "PriceHistory{" +
                "id=" + id +
                ", price=" + price +
                ", validDate=" + validDate.getDate() +
                ", productId=" + productId +
 //               ", product=" + productE +
                '}';
    }
}
