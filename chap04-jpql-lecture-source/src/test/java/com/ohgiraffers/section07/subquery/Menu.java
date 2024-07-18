package com.ohgiraffers.section07.subquery;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "menu_section07")
@Table(name = "tbl_menu")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
public class Menu {

    @Id
    @Column(name = "menu_code")
    private int menuCode;

    @Column(name = "menu_name")
    private String menuName;

    @Column(name = "menu_price")
    private int menuPrice;

    @Column(name = "category_code")
    private int categoryCode;

    @Column(name = "orderable_status")
    private String orderableStatus;
}
