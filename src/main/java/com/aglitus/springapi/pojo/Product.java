package com.aglitus.springapi.pojo;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="products")

public class Product extends AbstractEntity<Integer> {
    
    @Column(length = 150, nullable = false)
	@Getter @Setter private String name;
	
	@Column(nullable = true)
	@Getter @Setter private int quantity;

	@Column(nullable=false)
	@Getter @Setter private BigDecimal unitaryPrice;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	@Getter @Setter private Category category;
	
	// @ManyToOne
	// @JoinColumn(name="idfornecedor")
	// private Fornecedor fornecedor;
    
}
