package com.ohgiraffers.section04.enumtype;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/*
 * @Entity 어노테이션은 JPA에서 사용되는 엔티티 클래스임을 표시한다.
 * 이 어노테이션을 사용하면 해당 클래스가 데이터베이스의 테이블과 매핑된다.
 * @Entity 어노테이션은 클래스 선언 위에 위치해야 한다.
 * 또한, `name` 속성을 사용하여 엔티티 클래스와 매핑될 테이블의 이름을 지정할 수 있다.
 * 생략하면 자동으로 클래스 이름을 엔티티 명으로 사용한다.
 */

//@Entity(name = "member_section04")
@Table(name = "tbl_member_section04")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Member {

    @Id
    @Column(name = "member_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberNo;

    @Column(name = "member_id")
    private String memberId;

    @Column(name = "member_pwd")
    private String memberPwd;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "enroll_date")
    private LocalDate enrollDate;

    @Column(name = "member_role")
//    @Enumerated(EnumType.ORDINAL)
    @Enumerated(EnumType.STRING)
    private RoleType memberRole;

    @Column(name = "status")
    private String status;

}
