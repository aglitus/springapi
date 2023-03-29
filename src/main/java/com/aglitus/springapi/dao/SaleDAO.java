package com.aglitus.springapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.aglitus.springapi.pojo.Sale;

@Repository
public interface SaleDAO extends JpaRepository<Sale, Integer> { }