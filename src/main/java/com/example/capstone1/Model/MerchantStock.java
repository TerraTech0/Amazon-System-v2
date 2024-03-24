package com.example.capstone1.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "MerchantStock")
public class MerchantStock {

    @NotNull(message = "id can't be empty!")
    @Id
    @Column(columnDefinition = "int(10) not null")
    private Integer id;

    @NotNull(message = "product id can't be empty!")
    @Column(columnDefinition = "int(10) not null unique")
    private Integer productId;

    @NotNull(message = "merchant id can't be empty!")
    @Column(columnDefinition = "int(10) not null unique")
    private Integer merchantId;

    @NotNull(message = "stock can't be null!")
    @Min(value = 10, message = "stock has to be more than 10 at start")
    @Column(columnDefinition = "int(5) not null")
    private Integer stock;
}
