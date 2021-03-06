package com.inventi.codingchallenge.services;

import com.inventi.codingchallenge.models.BankOperation;
import com.inventi.codingchallenge.models.CurrencyCode;
import com.inventi.codingchallenge.repositories.IAccountBalanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public final class BankService {
    @Autowired
    private IAccountBalanceRepo accountBalanceRepo;

    @Autowired
    private IForexService forexService;

    public BankService(IAccountBalanceRepo accountBalanceRepo)
    {
        this.accountBalanceRepo = accountBalanceRepo;
    }

    public String getBankStatementCSV(List<String> bankAccounts, LocalDateTime from, LocalDateTime to)
    {
        List<BankOperation> operations = accountBalanceRepo.getOperationsForAccounts(bankAccounts, from, to);
        StringBuilder rez = new StringBuilder();
        for (BankOperation op: operations) {
            rez.append(op.toCSVRow());
        }
        if(rez.length() > 1 && rez.charAt(rez.length() - 1) == '\n' && rez.charAt(rez.length() - 2) == ',')
        {
            rez.deleteCharAt(rez.length() - 1);
            rez.deleteCharAt(rez.length() - 1);
        }
        return rez.toString();
    }

    public void insertBankStatement(String bankOperationsString)
    {
        ArrayList<BankOperation> bankOperations = new ArrayList<>();
        String[] operationStrings = bankOperationsString.split("\n");
        for (String opString : operationStrings) {
            if(!opString.equals(""))
            {
                bankOperations.add(new BankOperation(opString));
            }
        }
        accountBalanceRepo.insertBankOperations(bankOperations);
    }

    public BigDecimal getBalance(String account, LocalDateTime date) {
        ArrayList<BankOperation> bankOperations = accountBalanceRepo.getOperationsForAccounts(Arrays.asList(account), null, date);
        BigDecimal counter = new BigDecimal(0);
        for (BankOperation bo : bankOperations) {
            //BigDecimal rate = forexService.getHistoricalRate(bo.getCurrency(), CurrencyCode.EUR, date);
            BigDecimal rate = new BigDecimal(1);
            BigDecimal amt = bo.getAmount().multiply(rate);
            if(account.equals(bo.getBeneficiaryNumber()))
            {
                counter = counter.add(amt);
            }
            else {
                counter = counter.subtract(amt);
            }
        }
        return counter;
    }
}
