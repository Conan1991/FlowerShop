package com.doronin.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "ORDERS", schema = "PUBLIC", catalog = "TEST")
public class OrdersEntity {
    private Integer id;
    private Date opendate;
    private Date closedate;
    private String status;
    private String login;
    private BigDecimal total;

    @Id
    @Column(name = "ID")
    @SequenceGenerator( name = "orders_seq", sequenceName = "orders_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "OPENDATE", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getOpendate() {
        return opendate;
    }

    public void setOpendate(Date opendate) {
        this.opendate = opendate;
    }

    @Basic
    @Column(name = "CLOSEDATE", insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getClosedate() {
        return closedate;
    }

    public void setClosedate(Date closedate) {
        this.closedate = closedate;
    }

    @Basic
    @Column(name = "STATUS")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrdersEntity that = (OrdersEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(opendate, that.opendate) &&
                Objects.equals(closedate, that.closedate) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, opendate, closedate, status);
    }

    @Override
    public String toString() {
        return "OrdersEntity{" +
                "id=" + id +
                ", opendate=" + opendate +
                ", closedate=" + closedate +
                ", status='" + status + '\'' +
                ", login='" + login + '\'' +
                ", total=" + total +
                '}';
    }

    @Basic
    @Column(name = "TOTAL")
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Basic
    @Column(name = "LOGIN")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
