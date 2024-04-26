package edu.hneu.mjt.makarenkonazar.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Subscription {
    private String bankcard;
    private LocalDate startDate;

    public Subscription(String bankcard, LocalDate startDate) {
        this.bankcard = bankcard;
        this.startDate = startDate;
    }

    public String getBankcard() {
        return bankcard;
    }

    public void setBankcard(String bankcard) {
        this.bankcard = bankcard;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return String.format("%s - %s", bankcard, startDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }
}
