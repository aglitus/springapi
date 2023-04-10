package com.aglitus.springapi.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aglitus.springapi.dao.ProductDAO;
import com.aglitus.springapi.pojo.Product;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductDAO dao;

    @PostMapping("")
    public ResponseEntity<Product> save(@Valid @RequestPart("body") Product obj, @Valid @RequestPart("file") MultipartFile file) {

        try {

            Random random = new Random();
            String imageName = random.nextInt() + file.getOriginalFilename();
            obj.setImage(imageName);

            byte[] bytes = file.getBytes();
            Path path = Paths
                    .get(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\image\\" + imageName);
            Files.write(path, bytes);

            return new ResponseEntity<Product>(dao.save(obj), HttpStatus.OK);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("")
    public ResponseEntity<List<Product>> list() {

        try {
            List<Product> repository = dao.findAll();
            return new ResponseEntity<>(repository, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> get(@PathVariable(value = "id") int id) {

        try {
            Optional<Product> Product = dao.findById(id);
            return new ResponseEntity<Product>(Product.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable(value = "id") int id, @Valid @RequestPart("body") Product obj, @Valid @RequestPart("file") MultipartFile file) {

        try {
            Optional<Product> Product = dao.findById(id);

            if (Product.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } else {
                obj.setId(Product.get().getId());
                Random random = new Random();
                String imageName = random.nextInt() + file.getOriginalFilename();
                obj.setImage(imageName);

                byte[] bytes = file.getBytes();
                Path path = Paths
                    .get(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\image\\" + imageName);
                Files.write(path, bytes);

                return new ResponseEntity<Product>(dao.save(obj), HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathVariable(value = "id") int id) {

        try {

            Optional<Product> Product = dao.findById(id);
            if (Product.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                dao.delete(Product.get());
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);

        }

    }

    @GetMapping("/image/{name}")
    public HttpEntity<byte[]> download(@PathVariable(value = "name") String name) throws IOException {

        byte[] archive = Files.readAllBytes(
                Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\image\\" + name));
        HttpHeaders httpHeaders = new HttpHeaders();
        switch (name.substring(name.lastIndexOf(".") + 1).toUpperCase()) {
            case "JPG":
                httpHeaders.setContentType(MediaType.IMAGE_JPEG);
                break;
            case "GIF":
                httpHeaders.setContentType(MediaType.IMAGE_GIF);
                break;
            case "PNG":
                httpHeaders.setContentType(MediaType.IMAGE_PNG);
                break;

            default:
                break;
        }
        httpHeaders.setContentLength(archive.length);
        HttpEntity<byte[]> entity = new HttpEntity<byte[]>(archive, httpHeaders);
        return entity;
    }

}
