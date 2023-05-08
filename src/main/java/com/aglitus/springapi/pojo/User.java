package com.aglitus.springapi.pojo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

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
@JsonIdentityInfo(scope = User.class, generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class User implements UserDetails {

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
    private String username;

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
    private UserType userType;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Sale> sales;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
