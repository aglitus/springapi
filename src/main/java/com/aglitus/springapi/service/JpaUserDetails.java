package com.aglitus.springapi.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aglitus.springapi.dao.UserDAO;
import com.aglitus.springapi.pojo.User;

@Service
public class JpaUserDetails implements UserDetailsService {

    private final UserDAO dao;

    public JpaUserDetails(UserDAO dao){
        this.dao = dao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        return dao.findByUsername(username);
    }

}
