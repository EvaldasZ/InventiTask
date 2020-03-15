package com.inventi.codingchallenge.services;

import com.inventi.codingchallenge.models.CurrencyCode;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public final class ForexService implements IForexService {
    public BigDecimal getHistoricalRate(CurrencyCode from, CurrencyCode to, LocalDateTime date)
    {
        return new BigDecimal(1);
    }
}
