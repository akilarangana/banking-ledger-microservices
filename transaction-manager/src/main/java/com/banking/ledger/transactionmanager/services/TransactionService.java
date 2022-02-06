package com.banking.ledger.transactionmanager.services;

import com.banking.ledger.transactionmanager.beans.AccountBalance;
import com.banking.ledger.transactionmanager.beans.Transaction;
import com.banking.ledger.transactionmanager.constants.TransactionType;
import com.banking.ledger.transactionmanager.repositories.TransactionRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@Service
public class TransactionService {

    private static final String USER_MANAGER_NAME = "USER-MANAGER";
    private static final String ACCOUNT_MANAGER_NAME = "ACCOUNT-MANAGER";

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    private EurekaClient eurekaClient;
    @Autowired
    private RestTemplate restTemplate;

    public Transaction getTransactionById(int transactionId){
        return transactionRepository.getByTransactionReferenceAndRecordStatus(transactionId,0);
    }

    public Transaction createTransaction(Transaction transaction,String userName,String token){
        if(isValidUserNameTokenCombination(userName,token)){
            transaction.setRecordStatus(0);
            transaction.setTransactionDateTime(new Date());
            Transaction createdTransaction =  transactionRepository.saveAndFlush(transaction);
            createdTransaction.setTransactionReference(createdTransaction.getTransactionId());
            updateAccountBalance(transaction);
            return transactionRepository.saveAndFlush(createdTransaction);
        }
        return null;
    }

    public Transaction updateTransaction(Transaction transaction,String userName,String token){
        if(isValidUserNameTokenCombination(userName,token)){
            Optional<Transaction> originalTransactionOpt = transactionRepository.findById(transaction.getTransactionReference());
            if(originalTransactionOpt != null){
                Transaction originalAccount = originalTransactionOpt.get();
                transaction.setTransactionId(0);
                transaction.setRecordStatus(0);
                transaction.setTransactionDateTime(new Date());
                originalAccount.setRecordStatus(1);
                Transaction createdBank =  transactionRepository.saveAndFlush(transaction);
                transactionRepository.saveAndFlush(originalAccount);
                updateAccountBalance(transaction);
                return createdBank;
            }
            else{
                return createTransaction(transaction,userName,token);
            }
        }
        else{
            return null;
        }
    }

    public List<Transaction> getTransactionsByUser(int userId){
        return transactionRepository.getByTransactionUserAndRecordStatus(userId,0);
    }

    public List<Transaction> getTransactionHistory(int transactionRef){
        return transactionRepository.getByTransactionReference(transactionRef);
    }

    private boolean isValidUserNameTokenCombination(String userName,String token){
        Application application = eurekaClient.getApplication(USER_MANAGER_NAME);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" +
                "auth/validate?userName=" + userName + "&token=" + token;
        try{
            ResponseEntity response = restTemplate.getForEntity(url, ResponseEntity.class);
            return response.getStatusCode().equals(HttpStatus.OK);
        }
        catch (Exception e){
            return false;
        }
    }

    private boolean updateAccountBalance(Transaction transaction){
        Application application = eurekaClient.getApplication(ACCOUNT_MANAGER_NAME);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" +
                "accountBalance/create";
        AccountBalance accountBalance = new AccountBalance();
        if(transaction.getTransactionType() == TransactionType.CREDIT.type){
            accountBalance.setAccountNumber(transaction.getDestinationAccNumber());
            accountBalance.setTransactionAmount(transaction.getTransactionAmount());
        }
        else{
            accountBalance.setAccountNumber(transaction.getOriginAccNumber());
            accountBalance.setTransactionAmount(-transaction.getTransactionAmount());
        }
        accountBalance.setTransactionId(transaction.getTransactionReference());

        AccountBalance response = restTemplate.postForObject(url,accountBalance, AccountBalance.class);
        return response != null;
    }
}
