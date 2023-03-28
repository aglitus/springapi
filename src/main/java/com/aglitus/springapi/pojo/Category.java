package com.aglitus.springapi.pojo;

import java.util.List;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="categories")

public class Category extends AbstractEntity<Integer> {

    @Column(length = 150, nullable = false)
	@Getter @Setter private String description;

	@OneToMany(mappedBy = "category")
	@Getter @Setter private List<Product> products;
    
}
