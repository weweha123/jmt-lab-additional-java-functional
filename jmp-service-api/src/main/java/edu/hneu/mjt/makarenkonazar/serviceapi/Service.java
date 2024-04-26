package edu.hneu.mjt.makarenkonazar.serviceapi;

import edu.hneu.mjt.makarenkonazar.model.BankCard;
import edu.hneu.mjt.makarenkonazar.model.Subscription;
import edu.hneu.mjt.makarenkonazar.model.User;

import java.util.List;
import java.util.Optional;

public interface Service {
    void subscribe(BankCard bankCard);

    Optional<Subscription> getSubscriptionByCardNumber(String cardNumber);

    List<User> getAllUsers();
}
