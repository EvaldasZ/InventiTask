package com.inventi.codingchallenge.controllers;

import com.inventi.codingchallenge.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
public final class AccountBalanceController {
    @Autowired
    private BankService bankService;

    @GetMapping(value = "/statements")
    public @ResponseBody String getStatement(@RequestParam(required = true) List<String> accounts,
                                            @RequestParam(required = false) String from,
                                             @RequestParam(required = false) String to) {
        LocalDateTime fromDate = null;
        LocalDateTime toDate = null;
        if(from != null)
        {
            fromDate = LocalDateTime.parse(from);
        }
        if(to != null)
        {
            toDate = LocalDateTime.parse(to);
        }
        return bankService.getBankStatementCSV(accounts, fromDate, toDate);
    }

    @PostMapping(value = "/statements")
    public void postStatement(@RequestBody String csv)
    {
        bankService.insertBankStatement(csv);
    }
}
