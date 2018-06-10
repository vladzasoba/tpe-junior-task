package io.github.vladzasoba.app.service;

import io.github.vladzasoba.app.model.Customer;
import io.github.vladzasoba.app.repository.AccountRepository;
import io.github.vladzasoba.app.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public List<Account> findAccountsByCustomerId(Long customerId) {
        List<Account> accounts =  findAll()
                .stream()
                .filter(account -> {
                        return account
                            .getCustomer()
                            .getCustomerId()
                            .equals(customerId);
                })
                .collect(Collectors.toList());
        return accounts;
    }

    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        accountRepository.findAll().forEach(accounts::add);
        return accounts;
    }

    @Override
    public Account findOne(Long accountId) {
        return accountRepository.findOne(accountId);
    }

    @Override
    public Account save(Long customerId, Double amount) {
        Account account = new Account();
        Customer customer = findAccountsByCustomerId(customerId).get(0).getCustomer();
        account.setAmount(amount);
        account.setCustomer(customer);
        return accountRepository.save(account);
    }

    @Override
    public Account save(Account account) {
        return account != null ? accountRepository.save(account) : null;
    }


}
