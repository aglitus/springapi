package com.aglitus.springapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.aglitus.springapi.dao.UserDAO;
import com.aglitus.springapi.dto.LoginDTO;
import com.aglitus.springapi.pojo.User;
import com.aglitus.springapi.service.TokenService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDAO dao;

    private final TokenService tokenService;

    public UserController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("")
    public ResponseEntity<User> save(@Valid @RequestBody User obj) {

        try {
            String password = new BCryptPasswordEncoder().encode(obj.getPassword());
		    obj.setPassword(password);
            return new ResponseEntity<User>(dao.save(obj), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("")
    public ResponseEntity<List<User>> list() {

        try {
            List<User> repository = dao.findAll();
            return new ResponseEntity<>(repository, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable(value = "id") int id) {

        try {
            Optional<User> User = dao.findById(id);
            return new ResponseEntity<User>(User.get(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable(value = "id") int id, @Valid @RequestBody User obj) {

        try {
            Optional<User> User = dao.findById(id);

            if (User.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            } else {
                obj.setId(User.get().getId());
                String password = new BCryptPasswordEncoder().encode(obj.getPassword());
		        obj.setPassword(password);
                return new ResponseEntity<User>(dao.save(obj), HttpStatus.OK);
            }

        } catch (Exception e) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable(value = "id") int id) {

        try {

            Optional<User> User = dao.findById(id);
            if (User.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                dao.delete(User.get());
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);

        }

    }

    @PostMapping("/login")
    public ResponseEntity<LoginDTO> token(Authentication authentication){

        LoginDTO dto = new LoginDTO(authentication.getPrincipal(), tokenService.generateToken(authentication));
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }
}