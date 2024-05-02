package com.volt.transactionapp.services;

import com.volt.transactionapp.dtos.TransactionDTO;
import com.volt.transactionapp.dtos.UserDTO;
import com.volt.transactionapp.models.transactions.Transaction;
import com.volt.transactionapp.models.users.User;
import com.volt.transactionapp.repositories.TransactionRepository;
import com.volt.transactionapp.repositories.UserRepository;
import com.volt.transactionapp.services.exceptions.generalException.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserService userService;

    public Transaction saveTransaction(TransactionDTO dto) throws Exception {
        Transaction transaction = new Transaction();

        User sender = userService.findUserById(dto.getSenderId());
        User receiver = userService.findUserById(dto.getReceiverId());

        transaction.setDateTime(LocalDateTime.now());
        transaction.setReceiver(receiver);
        transaction.setSender(sender);
        transaction.setValue(dto.getValue());

        userService.validateTransaction(sender, receiver, dto.getValue());
        return transactionRepository.save(transaction);
    }
    public void delete(Transaction transaction){
        transactionRepository.delete(transaction);
    }
    public void deleteTransactionByUserId(Long id){
        List<Transaction> transactions = this.getAll();
        for(Transaction t: transactions){
            if(Objects.equals(t.getSender().getId(), id) ||
                    Objects.equals(t.getReceiver().getId(), id)){
                this.delete(t);
            }
        }
    }
    public List<Transaction> getAll(){
        return transactionRepository.findAll();
    }

    public Optional<Transaction> getById(Long id){
        return Optional.ofNullable(this.transactionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Transactions not found")));
    }

    public List<Transaction> findAll() {
        return this.transactionRepository.findAll();
    }
}
