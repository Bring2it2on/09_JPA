package com.ohgiraffers.section06.compositekey.subsection02.IdClassTests;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "member_section06_sub02")
@Table(name = "tbl_member_section06_sub02")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@IdClass(MemberPK.class)
public class Member {

    @Id
    @Column(name = "member_no")
    private int memberNo;

    @Id
    @Column(name = "member_id")
    private String memberId;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;
}
