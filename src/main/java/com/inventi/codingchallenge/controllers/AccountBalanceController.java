package com.inventi.codingchallenge.controllers;

import com.inventi.codingchallenge.services.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class AccountBalanceController {
    @Autowired
    private BankService bankService;
}
