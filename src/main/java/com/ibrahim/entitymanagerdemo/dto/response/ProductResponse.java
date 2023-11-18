package com.ibrahim.entitymanagerdemo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private int id;
    private String name;
    private BigDecimal price;
    private String description;
    private String category;
    private String brand;
    private int stockQuantity;
}
