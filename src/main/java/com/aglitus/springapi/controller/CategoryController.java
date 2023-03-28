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
import com.aglitus.springapi.dao.CategoryDAO;
import com.aglitus.springapi.pojo.Category;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryDAO dao;

    @PostMapping("/")
    public ResponseEntity<Category> save(@RequestBody Category cat) {

        try {
            return new ResponseEntity<Category>(dao.save(cat), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/")
    public ResponseEntity<List<Category>> list() {

        try {
            List<Category> categories = dao.findAll();
            return new ResponseEntity<>(categories, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> get(@PathVariable(value = "id") int id) {

        try {
            Optional<Category> category = dao.findById(id);
            return new ResponseEntity<Category>(category.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Category>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathVariable(value = "id") int id, @RequestBody Category cat) {

        try {
            Optional<Category> category = dao.findById(id);

            if (category.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } else {
                cat.setId(category.get().getId());
                return new ResponseEntity<Category>(dao.save(cat), HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<Category>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathVariable(value = "id") int id) {

        try {

            Optional<Category> category = dao.findById(id);
            if (category.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                dao.delete(category.get());
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<Category>(HttpStatus.BAD_REQUEST);

        }

    }

}
