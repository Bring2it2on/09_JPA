package com.ohgiraffers.section08.namedquery;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class JpqlNamedQueryTests {

    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    @BeforeAll
    public static void initFactory() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpatest");
    }

    @BeforeEach
    public void initManager() {
        entityManager = entityManagerFactory.createEntityManager();
    }

    @AfterAll
    public static void closeFactory() {
        entityManagerFactory.close();
    }

    @AfterEach
    public void closeManager() {
        entityManager.close();
    }

    @Test
    @DisplayName("@NamedQuery - findAll")
    public void test1() {

        //given

        //when
        List<Menu> menuList =
                entityManager
                        .createNamedQuery(
                                "section08.namedquery.Menu.findAll",
                                Menu.class
                        )
                        .getResultList();

        //then
        assertThat(menuList).isNotNull();
        menuList.forEach(System.out::println);
    }

    // findByMenuName -> 메뉴 이름을 파라미터로 받고 일치하는 메뉴객체를 반환하는 JPQL
    @Test
    @DisplayName("@NamedQuery - findByMenuName")
    public void test2() {

        //given
        String menuName = "멸치튀김우동";
        //when
        List<Menu> menuList =
                entityManager.createNamedQuery(
                                "section08.namedquery.Menu.findByMenuName",
                                Menu.class
                        )
                        .setParameter("menuName",menuName)
                        .getResultList();

        //then
        assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }

    @Test
    @DisplayName("@NamedQuery - findByMenuName")
    public void test3() {

        //given
        String menuName = "국";
        //when
        List<Menu> menuList =
                entityManager.createNamedQuery(
                                "section08.namedquery.Menu.findByMenuNameConditional",
                                Menu.class
                        )
                        .setParameter("menuName",menuName)
                        .getResultList();

        //then
        assertNotNull(menuList);
        menuList.forEach(System.out::println);
    }

}
