package com.banking.ledger.usermanager.services;

import com.banking.ledger.usermanager.beans.User;
import com.banking.ledger.usermanager.reposirories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUserById(int userId){
        return userRepository.findById(userId).get();
    }

    public User createUser(User bank){
        User createdUser =  userRepository.saveAndFlush(bank);
        return createdUser;
    }
}
