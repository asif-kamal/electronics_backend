package com.post_it.electronics.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {

    private UUID id;
    private String name;
    private String code;
    private String description;
    private List<CategoryTypeDTO> categoryTypeDTOList;
}
