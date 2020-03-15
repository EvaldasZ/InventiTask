package com.inventi.codingchallenge.models;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class BankOperationTest {


    @Test
    void toCSVRow() {
        BankOperation bo = new BankOperation("0", "1", LocalDateTime.of(2020,12,1,0,0), "2", new BigDecimal(1.25), CurrencyCode.EUR);
        String rez = bo.toCSVRow();
        assertEquals("0, 1, 2020-12-01 00:00:00, 2, 1.25, EUR\n", rez);
    }
}