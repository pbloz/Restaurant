package com.karwowski.auth.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Pizza {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    // @OneToOne
    private Cake cake;
    // @OneToOne
    private Size size;
    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cake getCake() {
        return cake;
    }

    public void setCake(Cake cake) {
        this.cake = cake;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Pizza() {

    }

    public Pizza(String name, Cake cake, Size size, Double price) {

        this.name = name;
        this.cake = cake;
        this.size = size;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pizza pizza = (Pizza) o;

        if (!id.equals(pizza.id)) return false;
        if (!name.equals(pizza.name)) return false;
        if (cake != pizza.cake) return false;
        if (size != pizza.size) return false;
        return price.equals(pizza.price);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + cake.hashCode();
        result = 31 * result + size.hashCode();
        result = 31 * result + price.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cake=" + cake +
                ", size=" + size +
                ", price=" + price +
                '}';
    }
}
