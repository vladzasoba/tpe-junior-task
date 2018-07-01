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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    ModelAndView getStartPage() {
        return getCustomerPage();
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
        params.put("curCustomerId", id);

        return new ModelAndView("customer-accounts", params);
    }

    @RequestMapping(value = "/api/accounts", method = RequestMethod.POST)
    public @ResponseBody
    ModelAndView saveAccount(@RequestParam("customer_id") String customerId, @RequestParam("amount") String amount) {
        accountService.save(Long.valueOf(customerId), Double.valueOf(amount));
        return getAccountPage(Long.valueOf(customerId));
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
    ModelAndView performTransaction(@RequestParam("src_account_id") String srcAccountId,
                             @RequestParam("dst_account_id") String dstAccountId,
                             @RequestParam("amount") String amount,
                             @RequestParam("tx_type") String txType) {
        transactionService.save(null,
                Long.valueOf(srcAccountId),
                dstAccountId.equals("")? null : Long.valueOf(dstAccountId),
                Double.valueOf(amount),
                txType);
        return getAccountPage(accountService.findOne(Long.valueOf(srcAccountId)).getCustomer().getCustomerId());
    }

}
