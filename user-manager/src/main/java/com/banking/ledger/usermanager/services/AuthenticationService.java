package com.banking.ledger.usermanager.services;

import com.banking.ledger.usermanager.beans.User;
import com.banking.ledger.usermanager.reposirories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@Service
public class AuthenticationService {

    @Autowired
    UserRepository userRepository;

    Map<String,String> userNameTokenMap;

    public AuthenticationService(){
        userNameTokenMap = new HashMap<>();
    }

    public String addUserToken(String userName){
        UUID uuid = UUID.randomUUID();
        userNameTokenMap.put(userName,uuid.toString());
        return uuid.toString();
    }

    public String isUserCanAccess(String userName,String password){
        User user = userRepository.findByUserName(userName);
        if(user != null && user.getPassword().equals(password)){
            return addUserToken(userName);
        }
        else{
            return null;
        }
    }

    public boolean isTokenValid(String userName,String token){
        return userNameTokenMap.get(userName) != null && userNameTokenMap.get(userName).equals(token);
    }

}
