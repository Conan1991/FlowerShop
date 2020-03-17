package com.doronin.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class OrderedItemsEntityPK implements Serializable {
    private Integer orderId;
    private String login;

    @Column(name = "ORDER_ID")
    @Id
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    @Column(name = "LOGIN")
    @Id
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderedItemsEntityPK that = (OrderedItemsEntityPK) o;
        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(login, that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, login);
    }
}
