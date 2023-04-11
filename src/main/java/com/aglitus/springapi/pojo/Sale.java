package com.aglitus.springapi.pojo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "sales")
@JsonIdentityInfo(scope = Sale.class ,generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Sale {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal totalValue;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, columnDefinition = "DATE")
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate saleDate;

    @OneToMany(mappedBy = "sale")
    private List<ProductSale> products_sales;

}
