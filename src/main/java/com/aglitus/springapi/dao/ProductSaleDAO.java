package com.aglitus.springapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.aglitus.springapi.pojo.ProductSale;

@Repository
public interface ProductSaleDAO extends JpaRepository<ProductSale, Integer> { }