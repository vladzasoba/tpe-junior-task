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
        Transaction transaction = new Transaction();
        transaction.setTransacationDate(new Date());
        Account srcAccount = accountService.findOne(srcAccountId);
        transaction.setSrcAccount(srcAccount);

        if (txType.equalsIgnoreCase("Transfer")) {
            transaction.setDstAccount(accountService.findOne(dstAccountId));
            srcAccount.setAmount(srcAccount.getAmount() - amount);
        } else if (txType.equalsIgnoreCase("Charge")) {
            srcAccount.setAmount(srcAccount.getAmount() - amount);
        }
        srcAccount.setAmount(srcAccount.getAmount() + amount);

        transaction.setAmount(amount);
        transaction.setTransactionType(txType);
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> findAll() {
        List<Transaction> transactions = new ArrayList<>();
        transactionRepository.findAll().iterator().forEachRemaining(transaction -> transactions.add(transaction));
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
