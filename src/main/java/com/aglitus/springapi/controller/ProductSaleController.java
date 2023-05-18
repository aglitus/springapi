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
import com.aglitus.springapi.dao.ProductSaleDAO;
import com.aglitus.springapi.pojo.ProductSale;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/productSale")
public class ProductSaleController {

    @Autowired
    private ProductSaleDAO dao;

    @PostMapping("")
    public ResponseEntity<ProductSale> save(@Valid @RequestBody ProductSale obj) {

        try {
            return new ResponseEntity<ProductSale>(dao.save(obj), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("")
    public ResponseEntity<List<ProductSale>> list() {

        try {
            List<ProductSale> repository = dao.findAll();
            return new ResponseEntity<>(repository, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductSale> get(@PathVariable(value = "id") int id) {

        try {
            Optional<ProductSale> ProductSale = dao.findById(id);
            return new ResponseEntity<ProductSale>(ProductSale.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<ProductSale>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductSale> update(@PathVariable(value = "id") int id, @Valid @RequestBody ProductSale obj) {

        try {
            Optional<ProductSale> ProductSale = dao.findById(id);

            if (ProductSale.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } else {
                obj.setId(ProductSale.get().getId());
                return new ResponseEntity<ProductSale>(dao.save(obj), HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<ProductSale>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductSale> delete(@Valid @PathVariable(value = "id") int id) {

        try {

            Optional<ProductSale> ProductSale = dao.findById(id);
            if (ProductSale.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                dao.delete(ProductSale.get());
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<ProductSale>(HttpStatus.BAD_REQUEST);

        }

    }

}
