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
@Table(name = "userTypes")
@JsonIdentityInfo(scope = UserType.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class UserType {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Description is mandatory")
    @Column(length = 150, nullable = false)
    private String description;

    @JsonIgnore
    @OneToMany(mappedBy = "userType")
    private List<User> user;
}
