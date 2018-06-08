package io.github.vladzasoba.app.service;

import io.github.vladzasoba.app.model.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAccountsByCustomerId(Long customerId);
    List<Account> findAll();
    Account findOne(Long accountId);
}
