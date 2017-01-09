package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PriceHistory that = (PriceHistory) o;

        if (id != that.id) return false;
        if (price != that.price) return false;
        if (productId != that.productId) return false;
        return validDate != null ? validDate.equals(that.validDate) : that.validDate == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + price;
        result = 31 * result + (validDate != null ? validDate.hashCode() : 0);
        result = 31 * result + productId;
        return result;
    }

    @Override
    public String toString() {
        return "PriceHistory{" +
                "id=" + id +
                ", price=" + price +
                ", validDate=" + validDate +
                ", productId=" + productId +
                '}';
    }
}
