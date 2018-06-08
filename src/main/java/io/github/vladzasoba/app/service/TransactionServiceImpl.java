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
        System.out.println(srcAccount);
        transaction.setSrcAccount(srcAccount);

        Account dstAccount = null;
        if (dstAccountId != null) {
            dstAccount = accountService.findOne(dstAccountId);
        }
        System.out.println(dstAccount);
        if (txType.equalsIgnoreCase("Transfer") && dstAccount != null) {
            transaction.setDstAccount(dstAccount);
            srcAccount.setAmount(srcAccount.getAmount() - amount);
            dstAccount.setAmount(dstAccount.getAmount() + amount);
            accountService.save(srcAccount);
            accountService.save(dstAccount);
        } else if (txType.equalsIgnoreCase("Charge")) {
            srcAccount.setAmount(srcAccount.getAmount() - amount);
            accountService.save(srcAccount);
        }
        srcAccount.setAmount(srcAccount.getAmount() + amount);
        accountService.save(srcAccount);

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
