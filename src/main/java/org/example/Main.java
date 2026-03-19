package org.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Main {
    public static List<Expense> allExpenses = new ArrayList<>();

    @GetMapping("/expenses")
    public List<Expense> getExpenses() {
        return allExpenses;
    }
    public static void main(String[] args) {SpringApplication.run(Main.class, args);


        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        allExpenses.add(new Expense("Dinner with friends", new BigDecimal("45.50"), LocalDate.now(), "FOOD"));
        allExpenses.add(new Expense("Cinema Ticket", new BigDecimal("30.00"), LocalDate.now(), "ENTERTAINMENT"));

        System.out.println("--- WELCOME TO EXPENSE TRACKER PRO ---");

        while (running) {
            System.out.println("\n>>> MAIN MENU <<<");
            System.out.println("1. Add new expense");
            System.out.println("2. Show history and total");
            System.out.println("3. Filter by category");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("Enter description:  ");
                    String desc = scanner.nextLine();
                    System.out.println("Enter amount: ");
                    BigDecimal amount = new BigDecimal(scanner.nextLine());
                    System.out.println("Enter category (FOOD, ENTERTAINMENT, TRANSPORT, HEALTH, OTHER): ");
                    String category = scanner.nextLine().toUpperCase();

                    allExpenses.add(new Expense(desc, amount, LocalDate.now(), category));
                    System.out.println("Successfully added! ");
                    break;

                case "2":
                    System.out.println("\n--- EXPENSE HISTORY ---");
                    BigDecimal total = BigDecimal.ZERO;
                    for (Expense e : allExpenses) {
                        e.showExpense();
                        total = total.add(e.amount);
                    }
                    System.out.println("-----------------------------");
                    System.out.println("TOTAL SPENT: " + total + " PLN");
                    break;

                case "3":
                    System.out.println("Enter category to filter by: ");
                    String filterCat = scanner.nextLine();
                    BigDecimal filterTotal = BigDecimal.ZERO;

                    System.out.println("\n--- EXPENSES IN CATEGORY: " + filterCat.toUpperCase() + " ---");
                    for (Expense e : allExpenses) {
                        if (e.category.equalsIgnoreCase(filterCat)) {
                            e.showExpense();
                            filterTotal = filterTotal.add(e.amount);
                        }
                    }
                    System.out.println("------------------------");
                    System.out.println("TOTAL IN " + filterCat.toUpperCase() + ": " + filterTotal + " PLN");
                    System.out.println("------------------------");
                    break;

                case "0":
                    System.out.println("Quitting... Have a nice day!");
                    running = false;
                    break;

                default:
                    System.out.println("Unknown option! Try again. ");




            }


        }


    }
}