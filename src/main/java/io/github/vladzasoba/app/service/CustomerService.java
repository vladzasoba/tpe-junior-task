package io.github.vladzasoba.app.service;

import io.github.vladzasoba.app.model.Customer;

import java.util.List;

public interface CustomerService {
    Customer findOne(Long customerId);

    List<Customer> findAll();

    Customer save(String firstName, String lastName, Integer age);

    void delete(Long customerId);
}
