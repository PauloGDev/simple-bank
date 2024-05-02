package com.volt.transactionapp.repositories;

import com.volt.transactionapp.dtos.TransactionDTO;
import com.volt.transactionapp.models.transactions.Transaction;
import com.volt.transactionapp.models.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<Transaction> findById(Long id);
}
