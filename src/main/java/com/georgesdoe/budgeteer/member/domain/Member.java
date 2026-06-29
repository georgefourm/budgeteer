package com.georgesdoe.budgeteer.member.domain;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    private String name;
}