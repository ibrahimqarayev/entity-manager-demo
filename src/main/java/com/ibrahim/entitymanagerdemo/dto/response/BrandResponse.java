package com.ibrahim.entitymanagerdemo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandResponse {
    private int id;
    private String name;
    private String description;
}
