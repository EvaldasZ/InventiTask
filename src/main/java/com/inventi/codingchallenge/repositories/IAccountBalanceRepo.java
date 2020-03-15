package com.inventi.codingchallenge.repositories;

import com.inventi.codingchallenge.models.BankOperation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface IAccountBalanceRepo {
    void insertBankOperations(ArrayList<BankOperation> bankOperations);
    ArrayList<BankOperation> getOperationsForAccounts(List<String> bankAccounts, LocalDateTime from, LocalDateTime to);

}
