package com.aglitus.springapi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.aglitus.springapi.pojo.UserType;

@Repository
public interface UserTypeDAO extends JpaRepository<UserType, Integer> { }