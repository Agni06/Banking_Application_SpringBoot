package com.banking_application.MyBanking.Repository;

import com.banking_application.MyBanking.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountDAO extends JpaRepository<Account, Integer> {

}
