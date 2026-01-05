package Bank;

import java.util.InputMismatchException;
import java.util.Scanner;

public class OnlineBankingSystem {

    private static Scanner scanner = new Scanner(System.in);
    private static AccountDAO dao = new AccountDAO();

    public static void main(String[] args) {

        Account account = dao.loadAccount();
        boolean running = true;

        while (running) {
            showMenu();
            int choice = getChoice();

            try {
                switch (choice) {
                    case 1:
                        System.out.println("Account Holder: " + account.getHolderName());
                        System.out.println("Account Number: " + account.getAccountNumber());
                        System.out.println("Balance: ₹" + account.getBalance());
                        break;

                    case 2:
                        System.out.print("Enter deposit amount: ");
                        account.deposit(getAmount());
                        dao.saveAccount(account);
                        System.out.println("Deposit successful.");
                        break;

                    case 3:
                        System.out.print("Enter withdrawal amount: ");
                        account.withdraw(getAmount());
                        dao.saveAccount(account);
                        System.out.println("Withdrawal successful.");
                        break;

                    case 4:
                        running = false;
                        dao.saveAccount(account);
                        System.out.println("Logged out successfully.");
                        break;

                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n--- Online Banking Menu ---");
        System.out.println("1. View Account Details");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Logout");
        System.out.print("Choose option: ");
    }

    private static int getChoice() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.next();
            return -1;
        }
    }

    private static double getAmount() {
        try {
            return scanner.nextDouble();
        } catch (InputMismatchException e) {
            scanner.next();
            throw new IllegalArgumentException("Invalid amount.");
        }
    }
}
