package com.aglitus.springapi.pojo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank(message = "Name is mandatory")
    @Column(length = 150, nullable = false)
    private String name;

    @NotBlank(message = "Surname is mandatory")
    @Column(length = 150, nullable = false)
    private String surname;

    @NotBlank(message = "Document is mandatory")
    @Column(length = 150, nullable = false)
    private String document;

    @NotBlank(message = "Address is mandatory")
    @Column(length = 300, nullable = false)
    private String address;

    @NotBlank(message = "User is mandatory")
    @Column(length = 100, nullable = false)
    private String user;

    @NotBlank(message = "Password is mandatory")
    @Column(length = 150, nullable = false)
    private String password;

    @Column(length = 100, nullable = false)
    private String phoneNumber;

    @NotNull(message = "Birth date is mandatory")
    @Column(nullable = false, columnDefinition = "DATE")
    @DateTimeFormat(iso = ISO.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
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
