package com.volt.transactionapp.controllers;

import com.volt.transactionapp.dtos.TransactionDTO;
import com.volt.transactionapp.models.transactions.Transaction;
import com.volt.transactionapp.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    TransactionService service;
    @GetMapping
    public List<Transaction> findAll(){
        return service.findAll();
    }
    @GetMapping("/{id}")
    public Optional<Transaction> findById(@PathVariable Long id){
        return service.getById(id);
    }
    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO dto) throws Exception {
        Transaction transaction = service.saveTransaction(dto);
        return ResponseEntity.status(HttpStatus.OK).body(transaction);
    }
}
