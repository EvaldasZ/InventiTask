package com.inventi.codingchallenge.repositories;

import com.inventi.codingchallenge.models.BankOperation;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StaticAccountBalanceRepoTest {

    @BeforeEach
    void ClearCache()
    {
        StaticCache.BankOperations.clear();
    }

    @Test
    void shouldReturnSingleOperationForASingleAccount() {
        StaticAccountBalanceRepo repo = new StaticAccountBalanceRepo();
        StaticCache.BankOperations.add(new BankOperation("0", null, null, null, null, null));
        List<BankOperation> result = repo.getOperationsForAccounts(Arrays.asList("0"), null, null);
        assertTrue(result.size() == 1);
    }

    @Test
    void  shouldReturnMultipleOperationsForASingleAccount() {
        StaticAccountBalanceRepo repo = new StaticAccountBalanceRepo();
        StaticCache.BankOperations.add(new BankOperation("0", null, null, null, null, null));
        StaticCache.BankOperations.add(new BankOperation("0", null, null, null, null, null));
        List<BankOperation> result = repo.getOperationsForAccounts(Arrays.asList("0"), null, null);
        assertTrue(result.size() == 2);
    }

    @Test
    void  shouldReturnSingleOperationForADifferentAccount() {
        StaticAccountBalanceRepo repo = new StaticAccountBalanceRepo();
        StaticCache.BankOperations.add(new BankOperation("1", null, null, null, null, null));
        List<BankOperation> result = repo.getOperationsForAccounts(Arrays.asList("1"), null, null);
        assertTrue(result.size() == 1);
    }

    @Test
    void  shouldReturnNoneIfTheDateFromDoesNotMatch() {
        StaticAccountBalanceRepo repo = new StaticAccountBalanceRepo();
        StaticCache.BankOperations.add(new BankOperation("1", null, new Date(2020, 1, 1), null, null, null));
        List<BankOperation> result = repo.getOperationsForAccounts(Arrays.asList("1"), new Date(2020, 12, 12), null);
        assertTrue(result.size() == 0);
    }

    @Test
    void  shouldReturnNoneIfTheDateToDoesNotMatch() {
        StaticAccountBalanceRepo repo = new StaticAccountBalanceRepo();
        StaticCache.BankOperations.add(new BankOperation("1", null, new Date(2020, 1, 1), null, null, null));
        List<BankOperation> result = repo.getOperationsForAccounts(Arrays.asList("1"), null, new Date(2020, 12, 12));
        assertTrue(result.size() == 0);
    }

    @Test
    void  shouldReturnBetweenDates() {
        StaticAccountBalanceRepo repo = new StaticAccountBalanceRepo();
        StaticCache.BankOperations.add(new BankOperation("1", null, new Date(2020, 1, 1), null, null, null));
        StaticCache.BankOperations.add(new BankOperation("1", null, new Date(2020, 6, 1), null, null, null));
        StaticCache.BankOperations.add(new BankOperation("1", null, new Date(2020, 12, 1), null, null, null));
        List<BankOperation> result = repo.getOperationsForAccounts(Arrays.asList("1"), new Date(2020, 5, 1), new Date(2020, 7, 1));
        assertTrue(result.size() == 1);
    }

    @Test
    void  shouldReturnFromMultipleAccounts() {
        StaticAccountBalanceRepo repo = new StaticAccountBalanceRepo();
        StaticCache.BankOperations.add(new BankOperation("1", null, new Date(2020, 1, 1), null, null, null));
        StaticCache.BankOperations.add(new BankOperation("2", null, new Date(2020, 6, 1), null, null, null));
        StaticCache.BankOperations.add(new BankOperation("1", null, new Date(2020, 12, 1), null, null, null));
        List<BankOperation> result = repo.getOperationsForAccounts(Arrays.asList("1", "2"), null, null);
        assertTrue(result.size() == 3);
    }
}