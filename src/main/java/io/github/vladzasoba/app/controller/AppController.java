package io.github.vladzasoba.app.controller;

import io.github.vladzasoba.app.model.Account;
import io.github.vladzasoba.app.model.Customer;
import io.github.vladzasoba.app.model.Transaction;
import io.github.vladzasoba.app.service.AccountService;
import io.github.vladzasoba.app.service.CustomerService;
import io.github.vladzasoba.app.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AppController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @RequestMapping("/hello")
    public String getHelloWorld() {
        return "hello-world";
    }

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public ModelAndView getCustomerPage() {
        Map<String, Object> params = new HashMap<>();
        List<Customer> customers = customerService.findAll();
        params.put("customers", customers);

        return new ModelAndView("customers", params);
    }

    @RequestMapping(value = "/api/customers", method = RequestMethod.GET)
    public @ResponseBody
    List<Customer> getCustomersJSON() {
        return customerService.findAll();
    }

    @RequestMapping(value = "/api/customers", method = RequestMethod.POST)
    public ModelAndView saveCustomer(@RequestParam("first_name") String firstname,
                                 @RequestParam("last_name") String lastName,
                                 @RequestParam("age") String ageString) {
        customerService.save(firstname, lastName, Integer.valueOf(ageString));
        return getCustomerPage();
    }

    @RequestMapping(value = "/api/customers/{id}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteCustomer(@PathVariable("id") Long id) {
        customerService.delete(id);
        return "";
    }

    @RequestMapping(value = "/customers/{id}/accounts", method = RequestMethod.GET)
    public ModelAndView getAccountPage(@PathVariable("id") Long id) {
        Map<String, Object> params = new HashMap<>();
        List<Account> accounts = accountService.findAccountsByCustomerId(id);
        List<Customer> customers = customerService.findAll();
        params.put("accounts", accounts);
        params.put("customers", customers);

        return new ModelAndView("customer-accounts", params);
    }

    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    public ModelAndView getTransactionPage() {
        Map<String, Object> params = new HashMap<>();
        List<Transaction> transactions = transactionService.findAll();
        params.put("transactions", transactions);

        return new ModelAndView("transaction", params);
    }

    @RequestMapping(value = "/api/transactions", method = RequestMethod.GET)
    public @ResponseBody
    List<Transaction> getTransactionsJSON() {
        return transactionService.findAll();
    }

    @RequestMapping(value = "/api/transactions", method = RequestMethod.POST)
    public @ResponseBody
    Transaction performTransaction(String srcAccountId, String dstAccountId, String amount, String txType) {
        return transactionService.save(null,
                Long.valueOf(srcAccountId),
                Long.valueOf(dstAccountId),
                Double.valueOf(amount),
                txType);
    }

}
