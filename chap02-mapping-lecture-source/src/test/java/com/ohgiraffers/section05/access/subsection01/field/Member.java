package com.ohgiraffers.section05.access.subsection01.field;

import com.ohgiraffers.section04.enumtype.RoleType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

/*
* 필드 접근이 기본값이므로 설정을 제거해도 동일하게 작동한다.
* 클래스에 적용을 시키고 필드에도 적용을 시키면 필드에 적용시킨 내용이 우선적으로 적용이 된다.
* */

//@Entity(name = "member_section05_sub01")
@Table(name = "tbl_member_section05_sub01")
@NoArgsConstructor
@AllArgsConstructor
@Access(AccessType.FIELD) // 모든 필드에 대해서 필드 직접 접근 방식을 사용
@Setter
@ToString
public class Member {

    @Id
    @Column(name = "member_no")
    @Access(AccessType.FIELD)
    private int memberNo;

    @Column(name = "member_id")
    @Access(AccessType.FIELD)
    private String memberId;

    @Column(name = "member_pwd")
    @Access(AccessType.FIELD)
    private String memberPwd;

    @Column(name = "nickname")
    @Access(AccessType.FIELD)
    private String nickname;

}
