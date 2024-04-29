package com.volt.transactionapp.models.transactions;

import com.volt.transactionapp.models.users.User;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "tb_transactions")
@Table(name = "tb_transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dateTime;
    @JoinColumn(name = "id_sender")
    @ManyToOne
    private User sender;
    @JoinColumn(name = "id_receiver")
    @ManyToOne
    private User receiver;
    private BigDecimal value;

    public Transaction() {
    }

    public Transaction(LocalDateTime dateTime, User sender, User receiver, BigDecimal value) {
        this.dateTime = dateTime;
        this.sender = sender;
        this.receiver = receiver;
        this.value = value;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
