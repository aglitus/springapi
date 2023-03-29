package com.aglitus.springapi.pojo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal totalValue;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference(value="sale-user")
    private User user;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate saleDate;

    @OneToMany(mappedBy = "sale")
    @JsonManagedReference(value="sale-products_sales")
    private List<ProductSale> products_sales;

}
