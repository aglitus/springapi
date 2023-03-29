package com.aglitus.springapi.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "cities")
public class City {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String state;

    @OneToMany(mappedBy = "city")
    @JsonManagedReference(value="user-city")
    private List<User> users;

    @OneToMany(mappedBy = "city")
    @JsonManagedReference(value="provider-city")
    private List<Provider> providers;

}
