package org.example;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Expense {
    public String id;
    public String description;
    public BigDecimal amount;
    public LocalDate date;
    public String category;

    public Expense() {
        this.id = java.util.UUID.randomUUID().toString();
    }


    public Expense(String description, BigDecimal amount, LocalDate date, String category) {
        this.id = java.util.UUID.randomUUID().toString();
        this.description = description;
        this.amount = amount;
        this.date = date;
        this.category = category;
    }

    public void showExpense() {
        System.out.println(date + " | " + category + " | " + description + ": " + amount + " PLN" );
    }
}
