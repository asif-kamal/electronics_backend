package com.post_it.electronics.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private String brand;
    private boolean isNewArrival;
    private UUID categoryId;
    private UUID categoryTypeId;
    private List<ProductVariantDTO> productVariantDTOList;
    private List<ProductResourceDTO> productResourceDTOList;
}
