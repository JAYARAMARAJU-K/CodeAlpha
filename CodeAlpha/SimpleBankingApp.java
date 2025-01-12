
import java.util.Scanner;

public class SimpleBankingApp 
{
    private static double balance = 0;
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        while (!quit) {
            System.out.println("Welcome to the Simple Banking App");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Quit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            switch (choice) 
            {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    deposit(scanner);
                    break;
                case 3:
                    withdraw(scanner);
                    break;
                case 4:
                    quit = true;
                    System.out.println("Thank you for using our banking app!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    private static void checkBalance() 
    {
        System.out.printf("Your balance is: %.2f%n", balance);
    }

    private static void deposit(Scanner scanner) 
    {
        System.out.print("Enter deposit amount: ");
        double amount = scanner.nextDouble();
        if (amount > 0) {
            balance += amount;
            System.out.printf("You deposited: %.2f%n", amount);
        } else {
            System.out.println("Invalid amount. Please enter a positive value.");
        }
    }

    private static void withdraw(Scanner scanner) 
    {
        System.out.print("Enter withdrawal amount: ");
        double amount = scanner.nextDouble();
        if (amount > 0 && amount <= balance) 
        {
            balance -= amount;
            System.out.printf("You withdrew: %.2f%n", amount);
        } 
        else if (amount > balance) 
        {
            System.out.println("Insufficient funds.");
        } 
        else 
        {
            System.out.println("Invalid amount. Please enter a positive value.");
        }
    }
}
