package com.aglitus.springapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.aglitus.springapi.pojo.Product;

@Repository
public interface ProductDAO extends JpaRepository<Product, Integer> { }