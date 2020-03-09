package com.doronin.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "FLOWERS_USERS", schema = "PUBLIC", catalog = "TEST")
public class FlowersUsersEntity {
   private Integer id;
   private String login;
   private String password;
   private String fio;
   private String address;
   private Integer balance;
   private Integer discount;

   @Id
   @Column(name = "ID")
   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   @Basic
   @Column(name = "LOGIN")
   public String getLogin() {
      return login;
   }

   public void setLogin(String login) {
      this.login = login;
   }

   @Basic
   @Column(name = "PASSWORD")
   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   @Basic
   @Column(name = "FIO")
   public String getFio() {
      return fio;
   }

   public void setFio(String fio) {
      this.fio = fio;
   }

   @Basic
   @Column(name = "ADDRESS")
   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   @Basic
   @Column(name = "BALANCE")
   public Integer getBalance() {
      return balance;
   }

   public void setBalance(Integer balance) {
      this.balance = balance;
   }

   @Basic
   @Column(name = "DISCOUNT")
   public Integer getDiscount() {
      return discount;
   }

   public void setDiscount(Integer discount) {
      this.discount = discount;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      FlowersUsersEntity that = (FlowersUsersEntity) o;

      if (!Objects.equals(id, that.id)) return false;
      if (!Objects.equals(login, that.login)) return false;
      if (!Objects.equals(password, that.password)) return false;
      if (!Objects.equals(fio, that.fio)) return false;
      if (!Objects.equals(address, that.address)) return false;
      if (!Objects.equals(balance, that.balance)) return false;
      if (!Objects.equals(discount, that.discount)) return false;

      return true;
   }

   @Override
   public int hashCode() {
      int result = id != null ? id.hashCode() : 0;
      result = 31 * result + (login != null ? login.hashCode() : 0);
      result = 31 * result + (password != null ? password.hashCode() : 0);
      result = 31 * result + (fio != null ? fio.hashCode() : 0);
      result = 31 * result + (address != null ? address.hashCode() : 0);
      result = 31 * result + (balance != null ? balance.hashCode() : 0);
      result = 31 * result + (discount != null ? discount.hashCode() : 0);
      return result;
   }
}
