package com.ibrahim.entitymanagerdemo.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequest {
    private String name;
    private BigDecimal price;
    private String description;
    private String categoryName;
    private String brandName;
    private int stockQuantity;
}
