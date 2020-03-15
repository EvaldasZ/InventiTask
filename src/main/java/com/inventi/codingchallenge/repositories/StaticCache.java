package com.inventi.codingchallenge.repositories;

import com.inventi.codingchallenge.models.BankOperation;

import java.util.concurrent.CopyOnWriteArrayList;

public final class StaticCache {
    public static final CopyOnWriteArrayList<BankOperation> BankOperations = new CopyOnWriteArrayList<>();
}
