package com.doronin.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ORDERED_ITEMS", schema = "PUBLIC", catalog = "TEST")
@IdClass(OrderedItemsEntityPK.class)
public class OrderedItemsEntity {
    private Integer orderId;
    private String login;
    private String nameFlower;
    private Integer ordered;

    @Id
    @Column(name = "ORDER_ID")
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Id
    @Column(name = "LOGIN")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "NAME_FLOWER")
    public String getNameFlower() {
        return nameFlower;
    }

    public void setNameFlower(String nameFlower) {
        this.nameFlower = nameFlower;
    }

    @Basic
    @Column(name = "ORDERED")
    public Integer getOrdered() {
        return ordered;
    }

    public void setOrdered(Integer ordered) {
        this.ordered = ordered;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedItemsEntity that = (OrderedItemsEntity) o;
        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(login, that.login) &&
                Objects.equals(nameFlower, that.nameFlower) &&
                Objects.equals(ordered, that.ordered);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, login, nameFlower, ordered);
    }
}
