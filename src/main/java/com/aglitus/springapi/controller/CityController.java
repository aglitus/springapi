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
import com.aglitus.springapi.dao.CityDAO;
import com.aglitus.springapi.pojo.City;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityDAO dao;

    @PostMapping("")
    public ResponseEntity<City> save(@Valid @RequestBody City obj) {

        try {
            return new ResponseEntity<City>(dao.save(obj), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("")
    public ResponseEntity<List<City>> list() {

        try {
            List<City> repository = dao.findAll();
            return new ResponseEntity<>(repository, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> get(@PathVariable(value = "id") int id) {

        try {
            Optional<City> City = dao.findById(id);
            return new ResponseEntity<City>(City.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<City>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<City> update(@PathVariable(value = "id") int id, @Valid @RequestBody City obj) {

        try {
            Optional<City> City = dao.findById(id);

            if (City.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } else {
                obj.setId(City.get().getId());
                return new ResponseEntity<City>(dao.save(obj), HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<City>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<City> delete(@PathVariable(value = "id") int id) {

        try {

            Optional<City> City = dao.findById(id);
            if (City.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                dao.delete(City.get());
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<City>(HttpStatus.BAD_REQUEST);

        }

    }

}
