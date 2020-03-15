package com.inventi.codingchallenge.services;

import com.inventi.codingchallenge.models.BankOperation;
import com.inventi.codingchallenge.models.CurrencyCode;
import com.inventi.codingchallenge.repositories.StaticAccountBalanceRepo;
import com.inventi.codingchallenge.repositories.StaticCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BankServiceTest {
    @BeforeEach
    void ClearCache()
    {
        StaticCache.BankOperations.clear();
    }

    private void SetupDefaultCache()
    {
        StaticCache.BankOperations.add(new BankOperation("0", "1", LocalDateTime.of(2020, 1, 1, 0, 0), "", new BigDecimal(10), CurrencyCode.EUR));
        StaticCache.BankOperations.add(new BankOperation("0", "1", LocalDateTime.of(2020, 6, 1, 0, 0), "", new BigDecimal(20), CurrencyCode.EUR));
        StaticCache.BankOperations.add(new BankOperation("0", "1", LocalDateTime.of(2020, 11, 1, 0, 0), "", new BigDecimal(30), CurrencyCode.EUR));
    }

    @Test
    void shouldCalculateBalanceTillPastDate() {
        SetupDefaultCache();
        StaticAccountBalanceRepo repo = new StaticAccountBalanceRepo();
        BankService service = new BankService(repo);
        BigDecimal rez = service.getBalance("1", LocalDateTime.of(2020,2,1,0,0));
        assertEquals(10.0, rez.doubleValue());
    }

    @Test
    void shouldCalculateBalanceAdd() {
        SetupDefaultCache();
        StaticAccountBalanceRepo repo = new StaticAccountBalanceRepo();
        BankService service = new BankService(repo);
        BigDecimal rez = service.getBalance("1", LocalDateTime.of(2020,11,2,0,0));
        assertEquals(60.0, rez.doubleValue());
    }

    @Test
    void shouldCalculateBalanceSubtract() {
        SetupDefaultCache();
        StaticCache.BankOperations.add(new BankOperation("1", "0", LocalDateTime.of(2020, 1, 1, 0, 0), "", new BigDecimal(10), CurrencyCode.EUR));

        StaticAccountBalanceRepo repo = new StaticAccountBalanceRepo();
        BankService service = new BankService(repo);
        BigDecimal rez = service.getBalance("1", LocalDateTime.of(2020,11,2,0,0));
        assertEquals(50.0, rez.doubleValue());
    }
}