package com.ohgiraffers.section09.nativequery;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MenuCategoryResponseDTO {
    private Long menuCode;
    private String menuName;
    private String categoryName;
}
