package edu.hneu.mjt.makarenkonazar.cloudbankimpl;

import edu.hneu.mjt.makarenkonazar.bankapi.Bank;
import edu.hneu.mjt.makarenkonazar.model.*;

import java.util.Random;
import java.util.stream.IntStream;

public class CloudBankImpl implements Bank {
    @Override
    public BankCard createBankCard(User user, BankCardType type) {
        var cardNumber = generateCardNumber();

        switch (type) {
            case CREDIT -> {
                return new CreditBankCard(cardNumber, user, 1000);
            }
            case DEBIT -> {
                return new DebitBankCard(cardNumber, user);
            }
        }

        throw new IllegalArgumentException("Unknown card type");
    }

    private String generateCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder("4");

        IntStream.range(0, 15 - 1).map(i -> random.nextInt(10)).forEach(cardNumber::append);

        var checkDigit = generateCheckDigit(cardNumber.toString());
        cardNumber.append(checkDigit);

        return cardNumber.toString();
    }

    private int generateCheckDigit(String cardNumber) {
        var sum = 0;
        var alternate = false;
        for (var i = cardNumber.length() - 1; i >= 0; i--) {
            var n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum * 9) % 10;
    }
}
