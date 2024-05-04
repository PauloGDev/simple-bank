package com.volt.transactionapp.controllers;

import com.volt.transactionapp.dtos.UserDTO;
import com.volt.transactionapp.models.users.User;
import com.volt.transactionapp.services.TransactionService;
import com.volt.transactionapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService service;
    @Autowired
    TransactionService transactionService;

    @GetMapping
    public List<User> findAllUsers() {
        return service.findAll();
    }
    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) throws Exception {
        return service.findUserById(id);
    }

    @PostMapping
    public void createUser(@RequestBody UserDTO user){
        service.saveUser(user);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO dto) throws Exception {
        User user = service.findUserById(id);
        dto = service.userToDTO(user, dto);
        service.saveUser(dto);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) throws Exception {
        transactionService.deleteTransactionByUserId(id);
        service.deleteUserById(id);
    }
}
