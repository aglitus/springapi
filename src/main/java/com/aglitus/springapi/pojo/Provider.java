package com.aglitus.springapi.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "providers")
@JsonIdentityInfo(scope = Provider.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
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
    private City city;

    @JsonIgnore
    @OneToMany(mappedBy = "provider")
    private List<Product> products;
}
