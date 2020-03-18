package com.doronin.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "FLOWERS", schema = "PUBLIC", catalog = "TEST")
public class FlowersEntity {
    private String name;
    private Integer price;
    private Integer amount;

    @Override
    public String toString() {
        return "FlowersEntity{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                '}';
    }

    @Id
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "PRICE")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "AMOUNT")
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlowersEntity that = (FlowersEntity) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, amount);
    }
}
