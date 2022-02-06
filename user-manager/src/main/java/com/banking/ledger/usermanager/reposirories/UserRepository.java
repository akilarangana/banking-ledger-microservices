package com.banking.ledger.usermanager.reposirories;

import com.banking.ledger.usermanager.beans.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUserName(String userName);
}
