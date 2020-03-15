package com.inventi.codingchallenge.repositories;

import com.inventi.codingchallenge.models.BankOperation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public final class StaticAccountBalanceRepo implements IAccountBalanceRepo {
    @Override
    public final void insertBankOperations(ArrayList<BankOperation> bankOperations) {
    }

    @Override
    public final ArrayList<BankOperation> getOperationsForAccounts(List<String> bankAccounts, Date from, Date to) {
        return null;
    }
}
