package com.ohgiraffers.section09.nativequery;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "menu_section09")
@Table(name = "tbl_menu")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@NamedNativeQueries({
        @NamedNativeQuery(
                name="section09.Menu.findByMenuCode",
                query = """
                    select
                        *
                    from
                        tbl_menu
                    where 
                        menu_code = ?
                """,
                resultClass = Menu.class
        ),
        @NamedNativeQuery(
                name="section09.Menu.findMenuAndCategory",
                query = """
                    select
                        menu_code "menuCode",
                        menu_name "menuName",
                    (select category_name
                        from tbl_category
                    where category_code = m.category_code) "categoryName"
                    from
                        tbl_menu m
                    where
                        menu_code = ?
                """,
                resultSetMapping = "menuCategoryResonseMap"
        )
})
@SqlResultSetMapping(
        name = "menuCategoryResonseMap",
        classes = @ConstructorResult(
                targetClass = MenuCategoryResponseDTO.class,
                columns = {
                        @ColumnResult(name="menuCode", type=Long.class),
                        @ColumnResult(name="menuName", type=String.class),
                        @ColumnResult(name="categoryName", type=String.class)
                })
)
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
