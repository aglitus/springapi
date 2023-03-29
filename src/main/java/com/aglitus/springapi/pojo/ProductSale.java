package com.aglitus.springapi.pojo;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "products_sales")
public class ProductSale {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_product")
    @JsonBackReference(value="product-products_sales")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "sale_id")
    @JsonBackReference(value="sale-products_sales")
    private Sale sale;

    @Column(nullable = false)
    private BigDecimal totalPrice;

}
