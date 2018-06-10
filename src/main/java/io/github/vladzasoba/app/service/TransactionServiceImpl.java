package io.github.vladzasoba.app.service;

import io.github.vladzasoba.app.model.Account;
import io.github.vladzasoba.app.repository.TransactionRepository;
import io.github.vladzasoba.app.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountService accountService;

    @Override
    public Transaction save(String txDate, Long srcAccountId, Long dstAccountId, Double amount, String txType) {
        Account srcAccount = accountService.findOne(srcAccountId);
        Account dstAccount = (dstAccountId != null) ? accountService.findOne(dstAccountId) : null;
        Transaction transaction = new Transaction();

        transaction.setTransacationDate(new Date());
        transaction.setSrcAccount(srcAccount);

        if (txType.equalsIgnoreCase("Transfer") && dstAccount != null) {
            transaction.setDstAccount(dstAccount);
            srcAccount.setAmount(srcAccount.getAmount() - amount);
            dstAccount.setAmount(dstAccount.getAmount() + amount);
        } else if (txType.equalsIgnoreCase("Charge")) {
            srcAccount.setAmount(srcAccount.getAmount() - amount);
        } else {
            srcAccount.setAmount(srcAccount.getAmount() + amount);
        }
        accountService.save(srcAccount);
        accountService.save(dstAccount);

        transaction.setAmount(amount);
        transaction.setTransactionType(txType);
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> findAll() {
        List<Transaction> transactions = new ArrayList<>();
        transactionRepository.findAll().iterator().forEachRemaining(transactions::add);
        return transactions;
    }

    @Override
    public void delete(Long txId) {

    }

    @Override
    public Transaction findOne(Long txId) {
        return null;
    }
}
