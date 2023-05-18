package com.aglitus.springapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.aglitus.springapi.pojo.City;

@Repository
public interface CityDAO extends JpaRepository<City, Integer> { }