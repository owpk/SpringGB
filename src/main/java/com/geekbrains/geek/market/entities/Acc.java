package com.geekbrains.geek.market.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@Table(name = "profiles")
public class Acc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "hobby")
    private String hobby;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}