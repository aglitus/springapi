package com.aglitus.springapi.pojo;

import java.math.BigDecimal;
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
@Table(name = "products")
public class Product {

	@Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(length = 150, nullable = false)
	private String name;

	@Column(nullable = true)
	private int quantity;

	@Column(nullable = false)
	private BigDecimal unitaryPrice;

	@JsonBackReference(value="category-product")
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@JsonBackReference(value="provider-product")
	@ManyToOne
	@JoinColumn(name = "provider_id")
	private Provider provider;

    @JsonManagedReference(value="product-products_sales")
	@OneToMany(mappedBy = "product")
	private List<ProductSale> products_sales;

}
