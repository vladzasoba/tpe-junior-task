package io.github.vladzasoba.app.repository;

import io.github.vladzasoba.app.model.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepository extends CrudRepository<Transaction, Long> {
    @Override
    Transaction save(Transaction transaction);

    @Override
    Iterable<Transaction> findAll();

    @Override
    void delete(Long aLong);

    @Override
    Transaction findOne(Long aLong);
}
