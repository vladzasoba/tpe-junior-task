package io.github.vladzasoba.app.repository;

import io.github.vladzasoba.app.model.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    @Override
    Customer findOne(Long aLong);

    @Override
    Iterable<Customer> findAll();

    @Override
    Customer save(Customer customer);

    @Override
    void delete(Customer customer);
}
