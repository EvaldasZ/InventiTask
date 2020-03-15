package com.inventi.codingchallenge.models;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;
import java.util.Objects;

public final class BankOperation {
    private String accountNumber;
    private String beneficiaryNumber;
    private LocalDateTime date;
    private String comment;
    private BigDecimal amount;
    private CurrencyCode currency;
    public static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public BankOperation(String accountNumber, String beneficiaryNumber, LocalDateTime date, String comment, BigDecimal amount, CurrencyCode currency) {
        this.accountNumber = accountNumber;
        this.beneficiaryNumber = beneficiaryNumber;
        this.date = date;
        this.comment = comment;
        this.amount = amount;
        this.currency = currency;
    }

    public BankOperation(String csvRow)
    {
        String[] variables = csvRow.split(",");
        accountNumber = variables[0].trim();
        beneficiaryNumber = variables[1].trim();
        date = LocalDateTime.parse(variables[2].trim(), dateFormatter);
        comment = variables[3].trim();
        amount =  new BigDecimal(variables[4].trim());
        currency = CurrencyCode.valueOf(variables[5].trim());
    }

    public String toCSVRow()
    {
        String comment = "";
        if(this.comment != null)
        {
            comment = this.comment;
        }
        return String.format("%s, %s, %s, %s, %s, %s\n",
                this.accountNumber,
                this.beneficiaryNumber,
                date.format(dateFormatter),
                comment,
                new Formatter(Locale.US).format("%.2f", this.amount),
                this.currency.toString());
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankOperation that = (BankOperation) o;
        return Objects.equals(getAccountNumber(), that.getAccountNumber()) &&
                Objects.equals(getBeneficiaryNumber(), that.getBeneficiaryNumber()) &&
                Objects.equals(getDate(), that.getDate()) &&
                Objects.equals(getComment(), that.getComment()) &&
                Objects.equals(getAmount(), that.getAmount()) &&
                getCurrency() == that.getCurrency();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccountNumber(), getBeneficiaryNumber(), getDate(), getComment(), getAmount(), getCurrency());
    }
}
