package com.product.springsecurityjwts.Entity;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="table_productinfo")
@JsonPropertyOrder({"id", "name", "price", "department"})


public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @NotNull(message="Product name should not be null")
    @NotEmpty(message="Product name should not be empty")
    private String name;

    private String price;
    @NotNull (message="Department name should not be null")
    @NotEmpty (message="Department name should not be empty")
    private String department;
}
