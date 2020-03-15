package com.inventi.codingchallenge.repositories;

import com.inventi.codingchallenge.models.BankOperation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public final class StaticAccountBalanceRepo implements IAccountBalanceRepo {
    @Override
    public final void insertBankOperations(ArrayList<BankOperation> bankOperations) {
    }

    @Override
    public final ArrayList<BankOperation> getOperationsForAccounts(List<String> bankAccounts, LocalDateTime from, LocalDateTime to) {
        List<BankOperation> list = StaticCache.BankOperations.stream()
                .filter(
                        x -> (
                                bankAccounts.contains(x.getAccountNumber()) &&
                                        ((from != null && x.getDate().isAfter(from)) || from == null) &&
                                        ((to != null && x.getDate().isBefore(to)) || to == null)
                        )
                )
                .collect(Collectors.toList());
        ArrayList<BankOperation> rez = new ArrayList<BankOperation>(list);
        return rez;
    }
}
