package com.example.capstone1.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "Category")
public class Category {

    @NotNull(message = "id can't be empty!")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "name can't be empty!")
    @Size(min = 3, message = "size must be more than 3 length long!")
    @Column(columnDefinition = "varchar(10) not null")
    private String name;
}
