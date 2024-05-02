package com.volt.transactionapp.services;

import com.volt.transactionapp.dtos.UserDTO;
import com.volt.transactionapp.models.users.User;
import com.volt.transactionapp.models.users.UserType;
import com.volt.transactionapp.repositories.UserRepository;
import com.volt.transactionapp.services.exceptions.generalException.ResourceNotFoundException;
import com.volt.transactionapp.services.exceptions.transactionException.InvalidTransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    void validateTransaction(User sender, User receiver, BigDecimal amount){
        if(sender.getUserType() == UserType.MERCHANT){
            throw new InvalidTransactionException("This user can't make transactions");
        }
        if(Objects.equals(sender.getId(), receiver.getId())){
            throw new InvalidTransactionException("Users cant't make transactions to himself");
        }
        if(sender.getAmount().compareTo(amount) < 0){
            throw new InvalidTransactionException("Insufficient balance");
        }

        sender.setAmount(sender.getAmount().subtract(amount));
        receiver.setAmount(receiver.getAmount().add(amount));
    }
    public List<User> findAll(){
        return this.repository.findAll();
    }
    public User findUserById(Long id) throws Exception {
       return this.repository.findUserById(id).orElseThrow(() -> new ResourceNotFoundException(
               "User with id: " + id + " not found"));
    }
    public void saveUser(UserDTO dto){
        User user = new User(dto);
        this.repository.save(user);
    }
    public void deleteUserById(Long id) throws Exception {
        User user = this.findUserById(id);
        this.repository.delete(user);
    }

    public UserDTO userToDTO(User user, UserDTO dto) {
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setAmount(dto.getAmount());
        user.setUserType(dto.getUserType());
        user.setDocument(dto.getDocument());
        return new UserDTO(user.getId(), user);
    }
}
