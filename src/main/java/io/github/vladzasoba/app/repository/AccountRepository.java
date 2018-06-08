package io.github.vladzasoba.app.repository;


import io.github.vladzasoba.app.model.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    @Override
    Iterable<Account> findAll();

    @Override
    Account findOne(Long accountId);

    @Override
    Account save(Account account);

    @Override
    void delete(Account account);


}
