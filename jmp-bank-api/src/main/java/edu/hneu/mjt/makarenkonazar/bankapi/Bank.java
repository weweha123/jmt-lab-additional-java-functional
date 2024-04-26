package edu.hneu.mjt.makarenkonazar.bankapi;

import edu.hneu.mjt.makarenkonazar.model.BankCard;
import edu.hneu.mjt.makarenkonazar.model.BankCardType;
import edu.hneu.mjt.makarenkonazar.model.User;

public interface Bank {
    BankCard createBankCard(User user, BankCardType type);
}