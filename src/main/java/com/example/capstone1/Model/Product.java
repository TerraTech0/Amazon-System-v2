package com.example.capstone1.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "Product")
public class Product {

    @NotNull(message = "id must not be empty")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @NotEmpty(message = "name can't be empty!")
    @Size(min = 3, message = "name must have more than 3 length long")
    @Column(columnDefinition = "varchar(15) not null")
    private String name;

    @NotNull(message = "price can't be null!")
    @Positive(message = "price must be positive numbers!")
    @Column(columnDefinition = "double not null default 0")
    private Double price;

    @NotNull(message = "category id can't be empty!")
    @Column(columnDefinition = "varchar(20) not null")
    private Integer categoryID;

    @Max(value = 5,message = "the rating range must be 0-5")
    @Positive
    @Column(columnDefinition = "int not null check(rating >= 0 and rating <= 5)")
    private Integer rating;

    @ElementCollection
    private List<String> reviews = new ArrayList<>();

}
