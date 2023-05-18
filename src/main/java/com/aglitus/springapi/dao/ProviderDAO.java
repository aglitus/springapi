package com.aglitus.springapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.aglitus.springapi.pojo.Provider;

@Repository
public interface ProviderDAO extends JpaRepository<Provider, Integer> { }