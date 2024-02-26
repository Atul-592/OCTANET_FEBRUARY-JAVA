import java.util.ArrayList;
import java.util.Scanner;

class Transaction 
{
    String type;
    double amount;

    public Transaction(String type, double amount) 
	{
        this.type = type;
        this.amount = amount;
    }
}

class ATM 
{
    private double balance;
    private ArrayList<Transaction> transactionHistory;

    public ATM(double initialBalance) 
	{
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

	public void withdraw(double amount) 
	{
        if (amount > 0 && amount <= balance) 
		{
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", amount));
            System.out.println("Withdrawal successful. Current balance: " + balance);
        } 
		else 
		{
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }

    public void deposit(double amount) 
	{
        if (amount > 0) 
		{
            balance += amount;
            transactionHistory.add(new Transaction("Deposit", amount));
            System.out.println("Deposit successful. Current balance: " + balance);
        } 
		else 
		{
            System.out.println("Invalid deposit amount.");
        }
    }

    public void transfer(double amount) 
	{
        if (amount > 0 && amount <= balance) 
		{
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter recipient's account number: ");
            String recipientAccount = scanner.nextLine();
            
            balance -= amount;
            transactionHistory.add(new Transaction("Transfer to " + recipientAccount, amount));
            System.out.println("Transfer successful. Current balance: " + balance);
        } 
		else 
		{
            System.out.println("Invalid transfer amount or insufficient balance.");
        }
    }

    public void displayTransactionHistory() 
	{
        System.out.println("Transaction History:");
        for (Transaction transaction : transactionHistory) 
		{
            System.out.println(transaction.type + ": " + transaction.amount);
        }
    }
}

public class ATMInterface_1 
{
	private static final String CORRECT_USER_ID = "UserName";
    private static final String CORRECT_PIN = "123ABC";
	
	private static boolean authenticateUser(String userID, String pin) 
	{
        return userID.equals(CORRECT_USER_ID) && pin.equals(CORRECT_PIN);
    }
	
    public static void main(String[] args) 
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter your User ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter your PIN: ");
        String pin = scanner.nextLine();

        if (!authenticateUser(userID, pin)) 
		{
            System.out.println("Invalid User ID or PIN.");
            return;
        }
		
        ATM atm = new ATM(1000); 	// Starting balance of Rs.1000

        int choice;

        do 
		{
            System.out.println("\n--------ATM Menu--------:");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Transfer");
            System.out.println("4. Transaction History");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) 
			{
                case 1:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(withdrawAmount);
                    break;
                case 2:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter transfer amount: ");
                    double transferAmount = scanner.nextDouble();
                    atm.transfer(transferAmount);
                    break;
                case 4:
                    atm.displayTransactionHistory();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }
}
