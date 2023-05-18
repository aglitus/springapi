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
import com.aglitus.springapi.dao.SaleDAO;
import com.aglitus.springapi.pojo.Sale;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    private SaleDAO dao;

    @PostMapping("")
    public ResponseEntity<Sale> save(@Valid @RequestBody Sale obj) {

        try {
            return new ResponseEntity<Sale>(dao.save(obj), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("")
    public ResponseEntity<List<Sale>> list() {

        try {
            List<Sale> repository = dao.findAll();
            return new ResponseEntity<>(repository, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> get(@PathVariable(value = "id") int id) {

        try {
            Optional<Sale> Sale = dao.findById(id);
            return new ResponseEntity<Sale>(Sale.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Sale>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Sale> update(@PathVariable(value = "id") int id, @Valid @RequestBody Sale obj) {

        try {
            Optional<Sale> Sale = dao.findById(id);

            if (Sale.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } else {
                obj.setId(Sale.get().getId());
                return new ResponseEntity<Sale>(dao.save(obj), HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<Sale>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Sale> delete(@PathVariable(value = "id") int id) {

        try {

            Optional<Sale> Sale = dao.findById(id);
            if (Sale.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                dao.delete(Sale.get());
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<Sale>(HttpStatus.BAD_REQUEST);

        }

    }

}
