package com.owntech.taskmanagement.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
}
