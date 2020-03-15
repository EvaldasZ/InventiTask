package com.inventi.codingchallenge.services;

import com.inventi.codingchallenge.repositories.IAccountBalanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

@Service
public final class BankService {
    @Autowired
    private IAccountBalanceRepo accountBalanceRepo;

    public BankService(IAccountBalanceRepo accountBalanceRepo)
    {
        this.accountBalanceRepo = accountBalanceRepo;
    }
}
