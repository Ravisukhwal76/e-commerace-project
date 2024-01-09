package com.ravi.major.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categories_id",referencedColumnName =  "categories_id")
    private Categories categories;

    private double price;

    private double weight;

    private String Description;

    private String imageName;


}
