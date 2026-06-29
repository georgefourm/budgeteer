package com.georgesdoe.budgeteer.member.domain;

import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "group_members",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    Set<Member> members;

}