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
import com.aglitus.springapi.dao.ProviderDAO;
import com.aglitus.springapi.pojo.Provider;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    private ProviderDAO dao;

    @PostMapping("")
    public ResponseEntity<Provider> save(@Valid @RequestBody Provider obj) {

        try {
            return new ResponseEntity<Provider>(dao.save(obj), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("")
    public ResponseEntity<List<Provider>> list() {

        try {
            List<Provider> repository = dao.findAll();
            return new ResponseEntity<>(repository, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Provider> get(@PathVariable(value = "id") int id) {

        try {
            Optional<Provider> Provider = dao.findById(id);
            return new ResponseEntity<Provider>(Provider.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Provider>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Provider> update(@PathVariable(value = "id") int id, @Valid @RequestBody Provider obj) {

        try {
            Optional<Provider> Provider = dao.findById(id);

            if (Provider.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } else {
                obj.setId(Provider.get().getId());
                return new ResponseEntity<Provider>(dao.save(obj), HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<Provider>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Provider> delete(@PathVariable(value = "id") int id) {

        try {

            Optional<Provider> Provider = dao.findById(id);
            if (Provider.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                dao.delete(Provider.get());
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<Provider>(HttpStatus.BAD_REQUEST);

        }

    }

}
