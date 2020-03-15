package com.inventi.codingchallenge.services;

import com.inventi.codingchallenge.models.CurrencyCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface IForexService {
    BigDecimal getHistoricalRate(CurrencyCode from, CurrencyCode to, LocalDateTime date);
}