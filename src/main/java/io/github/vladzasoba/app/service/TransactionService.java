package io.github.vladzasoba.app.service;

import io.github.vladzasoba.app.model.Transaction;

import java.util.List;

public interface TransactionService {
    Transaction save(String txDate, Long srcAccountId, Long dstAccountId, Double amount, String txType);

    List<Transaction> findAll();

    void delete(Long txId);

    Transaction findOne(Long txId);
}
