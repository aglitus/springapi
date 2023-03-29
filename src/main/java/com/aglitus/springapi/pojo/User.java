package com.aglitus.springapi.pojo;

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
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(length = 150, nullable = false)
    private String surname;

    @Column(length = 150, nullable = false)
    private String document;

    @Column(length = 300, nullable = false)
    private String address;

    @Column(length = 100, nullable = false)
    private String user;

    @Column(length = 150, nullable = false)
    private String password;

    @Column(length = 100, nullable = false)
    private String phoneNumber;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "userType_id")
    @JsonBackReference(value = "userType-user")
    private UserType userType;

    @ManyToOne
    @JoinColumn(name = "city_id")
    @JsonBackReference(value="user-city")
    private City city;

    @OneToMany(mappedBy = "user")
    @JsonManagedReference(value="sale-user")
    private List<Sale> sales;
}
