package com.banking.ledger.usermanager.controllers;

import com.banking.ledger.usermanager.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;

    /**
     * get the username and password as request params and check whether the given combination is matched
     * @param userName
     * @param password
     * @return ResponseEntity with ok or forbidden response
     */
    @GetMapping(path = "/authenticate")
    public ResponseEntity getCurrencyByCode(@RequestParam(value = "userName") String userName,
                                            @RequestParam(value = "password") String password) {
        String token = authenticationService.isUserCanAccess(userName,password);
        if(token != null){
            return new ResponseEntity<String>(token, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    /**
     * validate username against the token
     * @param userName
     * @param token
     * @return ResponseEntity with ok or forbidden response
     */
    @GetMapping(path = "/validate")
    public ResponseEntity validateToken(@RequestParam(value = "userName") String userName,
                                            @RequestParam(value = "token") String token) {
        boolean isValid = authenticationService.isTokenValid(userName,token);
        if(isValid){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}
