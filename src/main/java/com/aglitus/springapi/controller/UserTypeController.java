package com.aglitus.springapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aglitus.springapi.dao.UserTypeDAO;
import com.aglitus.springapi.pojo.UserType;

@RestController
@RequestMapping("/userType")
public class UserTypeController {

    @Autowired
    private UserTypeDAO dao;

    @PostMapping("/")
    public ResponseEntity<UserType> save(@RequestBody UserType obj) {

        try {
            return new ResponseEntity<UserType>(dao.save(obj), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/")
    public ResponseEntity<List<UserType>> list() {

        try {
            List<UserType> repository = dao.findAll();
            return new ResponseEntity<>(repository, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserType> get(@PathVariable(value = "id") int id) {

        try {
            Optional<UserType> UserType = dao.findById(id);
            return new ResponseEntity<UserType>(UserType.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<UserType>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<UserType> update(@PathVariable(value = "id") int id, @RequestBody UserType obj) {

        try {
            Optional<UserType> UserType = dao.findById(id);

            if (UserType.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } else {
                obj.setId(UserType.get().getId());
                return new ResponseEntity<UserType>(dao.save(obj), HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<UserType>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserType> delete(@PathVariable(value = "id") int id) {

        try {

            Optional<UserType> UserType = dao.findById(id);
            if (UserType.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                dao.delete(UserType.get());
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<UserType>(HttpStatus.BAD_REQUEST);

        }

    }

}
