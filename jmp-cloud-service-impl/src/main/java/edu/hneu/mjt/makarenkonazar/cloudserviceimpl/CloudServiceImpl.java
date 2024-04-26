package edu.hneu.mjt.makarenkonazar.cloudserviceimpl;

import edu.hneu.mjt.makarenkonazar.model.BankCard;
import edu.hneu.mjt.makarenkonazar.model.Subscription;
import edu.hneu.mjt.makarenkonazar.model.User;
import edu.hneu.mjt.makarenkonazar.serviceapi.Service;

import java.time.LocalDate;
import java.util.*;

public class CloudServiceImpl implements Service {
    Map<User, Subscription> subscriptions = new HashMap<>();

    @Override
    public void subscribe(BankCard bankCard) {
        var subscription = new Subscription(bankCard.getNumber(), LocalDate.now());
        subscriptions.put(bankCard.getUser(), subscription);
    }

    @Override
    public Optional<Subscription> getSubscriptionByCardNumber(String cardNumber) {
        return subscriptions.values()
                .stream()
                .filter(subscription -> subscription.getBankcard().equals(cardNumber))
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(subscriptions.keySet());
    }
}
