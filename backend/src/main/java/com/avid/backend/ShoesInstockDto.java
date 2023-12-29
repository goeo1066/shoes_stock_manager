package com.avid.backend;

import java.util.UUID;

public class ShoesInstockDto {
    private String id;
    private String name;
    private String size;
    private Integer qty;

    public ShoesInstockDto() {
    }

    public ShoesInstockDto(String id, String name, String size, Integer qty) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.qty = qty;

    }

    public static ShoesInstockDto from(ShoesInstockEntity entity) {
        return new ShoesInstockDto(entity.getId().toString().trim(), entity.getName().trim(), entity.getSize().trim(), entity.getQty());
    }

    public ShoesInstockEntity toEntity() {
        if (id != null && !id.isBlank()) {
            return new ShoesInstockEntity(UUID.fromString(id.trim()), name.trim(), size.trim(), qty);
        } else {
            return new ShoesInstockEntity(null, name.trim(), size.trim(), qty);
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
