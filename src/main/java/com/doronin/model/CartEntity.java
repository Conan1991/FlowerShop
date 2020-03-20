package com.doronin.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "CART", schema = "PUBLIC", catalog = "TEST")
@IdClass(CartEntityPK.class)
public class CartEntity {
    private Integer ordered;
    private BigDecimal sumPrice;
    private String login;
    private String name;

    @Basic
    @Column(name = "ORDERED")
    public Integer getOrdered() {
        return ordered;
    }

    public void setOrdered(Integer ordered) {
        this.ordered = ordered;
    }

    @Basic
    @Column(name = "SUM_PRICE")
    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartEntity that = (CartEntity) o;
        return Objects.equals(ordered, that.ordered) &&
                Objects.equals(sumPrice, that.sumPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ordered, sumPrice);
    }

    @Id
    @Column(name = "LOGIN")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Id
    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "CartEntity{" +
                "ordered=" + ordered +
                ", sumPrice=" + sumPrice +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }
}
