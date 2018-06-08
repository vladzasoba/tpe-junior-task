package io.github.vladzasoba.app.service;

import io.github.vladzasoba.app.repository.CustomerRepository;
import io.github.vladzasoba.app.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Customer findOne(Long customerId) {
        return customerRepository.findOne(customerId);
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers =  new LinkedList<Customer>();
        customerRepository.findAll().iterator().forEachRemaining(el -> customers.add(el));
        return customers;
    }

    @Override
    public Customer save(String firstName, String lastName, Integer age) {
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setAge(age);
        return customerRepository.save(customer);
    }

    @Override
    public void delete(Long customerId) {
        Customer customer = findOne(customerId);
        customerRepository.delete(customer);
    }
}
