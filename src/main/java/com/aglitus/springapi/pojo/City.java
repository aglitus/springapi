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
@Table(name = "cities")
@JsonIdentityInfo(scope = City.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class City {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Description is mandatory")
    private String description;

    @NotBlank(message = "State is mandatory")
    private String state;

    @JsonIgnore
    @OneToMany(mappedBy = "city")
    private List<User> users;

    @JsonIgnore
    @OneToMany(mappedBy = "city")
    private List<Provider> providers;

}
