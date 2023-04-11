package com.aglitus.springapi.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="categories")
@JsonIdentityInfo(scope = Category.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Category {

	@Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Description is mandatory")
    @Column(length = 150, nullable = false)
 	private String description;

	@OneToMany(mappedBy = "category")
	private List<Product> products;
    
}
