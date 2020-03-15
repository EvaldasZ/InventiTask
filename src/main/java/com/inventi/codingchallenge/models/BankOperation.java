package com.inventi.codingchallenge.models;

import java.math.BigDecimal;
import java.util.Date;

public final class BankOperation {
    private String accountNumber;
    private String beneficiaryNumber;
    private Date date;
    private String comment;
    private BigDecimal amount;
    private CurrencyCode currency;

    public BankOperation(String accountNumber, String beneficiaryNumber, Date date, String comment, BigDecimal amount, CurrencyCode currency) {
        this.accountNumber = accountNumber;
        this.beneficiaryNumber = beneficiaryNumber;
        this.date = date;
        this.comment = comment;
        this.amount = amount;
        this.currency = currency;
    }

    public String getCSVHeader()
    {
        return "";
    }

    public String toCSVRow()
    {
        return "";
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBeneficiaryNumber() {
        return beneficiaryNumber;
    }

    public void setBeneficiaryNumber(String beneficiaryNumber) {
        this.beneficiaryNumber = beneficiaryNumber;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public CurrencyCode getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyCode currency) {
        this.currency = currency;
    }
}
