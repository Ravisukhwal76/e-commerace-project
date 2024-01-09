package com.ravi.major.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categories_id")
    private int id;

    private String name;

}
