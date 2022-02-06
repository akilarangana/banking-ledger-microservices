package com.banking.ledger.bankmanager.services;

import com.banking.ledger.bankmanager.beans.Bank;
import com.banking.ledger.bankmanager.controllers.Response;
import com.banking.ledger.bankmanager.repositories.BankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class BankService {

    @Autowired
    BankRepository bankRepository;

    public Bank getBankById(int bankId){
        return bankRepository.findById(bankId).get();
    }

    public Bank createBank(Bank bank){
        Bank createdBank =  bankRepository.saveAndFlush(bank);
        return createdBank;
    }

    public Response deleteBankById(int bankId){

        bankRepository.deleteById(bankId);
        Response response = new Response();
        response.setResponseId(bankId);
        response.setResponseMsg("Bank deleted");
        return response;
    }

    public Bank getBankByIdentifier(String identifier){
        List<Bank> banks = bankRepository.findAll();
        for(Bank bank : banks){
            if(bank.getBankIdentifier().equalsIgnoreCase(identifier)){
                return bank;
            }
        }
        return null;
    }
}
