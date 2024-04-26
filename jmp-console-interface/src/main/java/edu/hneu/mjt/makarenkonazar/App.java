package edu.hneu.mjt.makarenkonazar;

import edu.hneu.mjt.makarenkonazar.bankapi.Bank;
import edu.hneu.mjt.makarenkonazar.cloudbankimpl.CloudBankImpl;
import edu.hneu.mjt.makarenkonazar.cloudserviceimpl.CloudServiceImpl;
import edu.hneu.mjt.makarenkonazar.model.BankCard;
import edu.hneu.mjt.makarenkonazar.model.BankCardType;
import edu.hneu.mjt.makarenkonazar.model.User;
import edu.hneu.mjt.makarenkonazar.serviceapi.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Service service = new CloudServiceImpl();
    private static final Bank bank = new CloudBankImpl();

    public static void main(String[] args) {
        while (true) {
            printMainMenu();
            int choice = getUserChoice(4);
            switch (choice) {
                case 1:
                    var user = createUser();
                    var card = createBankCard(user);
                    subscribe(card);
                    break;
                case 2:
                    getSubscriptionByCardNumber();
                    break;
                case 3:
                    getAllUsers();
                    break;
                case 4:
                    System.out.println("Exiting the application...");
                    return;
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("=== Bank Console App ===");
        System.out.println("1. Add your card and subscribe");
        System.out.println("2. Get subscription by card number");
        System.out.println("3. Get all users");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getUserChoice(int max) {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > max) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.print("Invalid input. Please enter a number between " + 1 + " and " + max + ": ");
            }
        }
        return choice;
    }

    private static User createUser() {
        System.out.println("\n=== Create User ===");
        System.out.print("Enter name: ");
        var name = scanner.nextLine();
        System.out.print("Enter surname: ");
        var surname = scanner.nextLine();
        LocalDate birthdate = null;
        while (birthdate == null) {
            System.out.print("Enter birthdate (dd/mm/yyyy): ");
            try {
                birthdate = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                if (birthdate.isAfter(LocalDate.now())) {
                    System.out.println("Birthdate cannot be in the future. Please try again.");
                    birthdate = null;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please try again.");
            }
        }
        System.out.println("User created successfully.\n");
        return new User(name, surname, birthdate);
    }

    private static BankCard createBankCard(User user) {
        System.out.println("\n=== Create Bank Card ===");
        System.out.println("Select bank card type:");
        System.out.println("1. Credit");
        System.out.println("2. Debit");
        var typeChoice = getUserChoice(2);
        BankCardType cardType = (typeChoice == 1) ? BankCardType.CREDIT : BankCardType.DEBIT;
        var card = bank.createBankCard(user, cardType);
        System.out.println("Bank card created successfully.\n");
        System.out.println("Your card number: " + card.getNumber() + "\n");
        return card;
    }

    private static void subscribe(BankCard bankCard) {
        System.out.println("\n=== Subscribing of Bank Card ===");
        service.subscribe(bankCard);
        System.out.println("Bank card subscribed successfully.\n");
    }

    private static void getAllUsers() {
        System.out.println("\n=== All Users ===");
        service.getAllUsers().forEach(System.out::println);
        System.out.println();
    }

    private static void getSubscriptionByCardNumber() {
        System.out.println("\n=== Subscription by Card Number ===");
        System.out.print("Enter card number: ");
        var cardNumber = scanner.nextLine();
        service.getSubscriptionByCardNumber(cardNumber).ifPresentOrElse((value) -> {
            System.out.println("Subscription found: " + value);
        }, () -> {
            System.out.println("No subscription found for this card number.");
        });
    }
}