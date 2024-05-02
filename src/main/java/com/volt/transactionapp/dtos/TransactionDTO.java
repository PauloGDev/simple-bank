package com.volt.transactionapp.dtos;

import com.volt.transactionapp.models.transactions.Transaction;
import com.volt.transactionapp.models.users.User;
import com.volt.transactionapp.services.TransactionService;
import com.volt.transactionapp.services.UserService;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionDTO {
    private Long senderId;
    private Long receiverId;
    private BigDecimal value;
    UserService service;

    public TransactionDTO() {
    }
    public TransactionDTO(User sender, User receiver, BigDecimal value) {
        this.senderId = sender.getId();
        this.receiverId = receiver.getId();
        this.value = value;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
