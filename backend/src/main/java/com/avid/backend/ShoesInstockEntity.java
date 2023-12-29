package com.avid.backend;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(schema = "shoes", name = "stocks")
public class ShoesInstockEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String size;
    private Integer qty;

    public ShoesInstockEntity() {
    }

    public ShoesInstockEntity(UUID id, String name, String size, Integer qty) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.qty = qty;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }
}
