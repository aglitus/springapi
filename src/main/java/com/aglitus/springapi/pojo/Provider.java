package com.aglitus.springapi.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "providers")
public class Provider {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Column(length = 150, nullable = false)
    private String name;

    @NotBlank(message = "Document is mandatory")
    @Column(length = 150, nullable = false)
    private String document;

    @NotBlank(message = "Address is mandatory")
    @Column(length = 300, nullable = false)
    private String address;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @JsonBackReference(value="provider-city")
    private City city;

    @OneToMany(mappedBy = "provider")
    @JsonManagedReference(value="provider-product")
    private List<Product> products;
}
