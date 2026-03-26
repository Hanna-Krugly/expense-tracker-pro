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
    @org.springframework.web.bind.annotation.PostMapping("/expenses")
    public Expense addExpense(@org.springframework.web.bind.annotation.RequestBody Expense expense) {
        if (expense.description == null || expense.description.isBlank()) {
            throw new IllegalArgumentException("Description is required");
        }
        if (expense.amount == null || expense.amount.doubleValue() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (expense.date == null) {
            expense.date = java.time.LocalDate.now();
        }
        allExpenses.add(expense);
        System.out.println("New expense added via Web: " + expense.description);
        saveToFile();
        return expense;
    }
    @org.springframework.web.bind.annotation.DeleteMapping("/expenses/{id}")
    public void deleteExpense(@org.springframework.web.bind.annotation.PathVariable String id) {
        allExpenses.removeIf(e -> e.id.equals(id));
        saveToFile();
        System.out.println("Deleted expense with ID: " + id);
    }

    public static void main(String[] args) {
        loadFromFile();
        SpringApplication.run(Main.class, args);


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
                    saveToFile();
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
    private static void saveToFile() {
        try (java.io.PrintWriter writer = new java.io.PrintWriter(new java.io.FileWriter("expenses.txt"))) {
            for (Expense e : allExpenses) {
                writer.println(e.id + ";" + e.description + ";" + e.amount + ";" + e.date + ";" + e.category);
            }
            System.out.println(">>> Data saved to expenses.txt <<<");
        } catch (java.io.IOException e) {
            System.out.println("Error while saving data: " + e.getMessage());
        }
    }
    private static void loadFromFile() {
        java.io.File file = new java.io.File("expenses.txt");
        if (!file.exists()) return;
        try (java.util.Scanner fileScanner = new java.util.Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String[] parts = fileScanner.nextLine().split(";");
                if (parts.length == 5) {
                    Expense e = new Expense(parts[1], new BigDecimal(parts[2]), LocalDate.parse(parts[3]), parts[4]);
                    e.id = parts[0];
                    allExpenses.add(e);
                }
            }
            System.out.println(">>> Data loaded from expenses.txt <<<");
        } catch (Exception e) {
            System.err.println("Error loading: " + e.getMessage());
        }
    }
}