package Algorithm.Company.Remitly;

import java.util.*;

/**
 * Balance Accounts to Threshold
 *
 * Problem:
 * Given N accounts with different balances and one threshold.
 *
 * You may transfer money between accounts so that every account
 * becomes exactly threshold.
 *
 * Need to:
 * 1. Verify whether balancing is possible
 *    - If impossible => throw error
 *
 * 2. If possible, print all transactions:
 *    sourceAccount -> destinationAccount : amount
 *
 * Input:
 * List<Account>, int threshold
 *
 * Example:
 * A0=200
 * A1=70
 * A2=30
 * A3=90
 * A4=110
 *
 * threshold = 100
 *
 * Output:
 * A0 -> A1 : 30
 * A0 -> A2 : 70
 * A4 -> A3 : 10
 *
 * Final:
 * all become 100
 */
public class BalanceAccounts {

    static class Transaction {
        String from;
        String to;
        double amount;

        public Transaction (String from, String to, double amount) {
            this.from = from;
            this.to = to;
            this.amount = amount;
        }

        @Override
        public String toString() {
            return "from: " + from + ",to: " + to + ",amount: " + amount;
        }
    }

    static class Account {
        String id;
        double balance;

        public Account (int id, double balance) {
            this.id = String.valueOf(id);
            this.balance = balance;
        }
    }

    /**
     * Return all transfer steps.
     */
    public List<Transaction> balance(List<Account> accounts, double threshold) {
        //TODO
        List<Transaction> res = new ArrayList<>();
        double sum = 0;
        for (Account account: accounts) {
            sum += account.balance;
        }

        if (sum != threshold * accounts.size()) {
            throw new RuntimeException("");
        }

        accounts.sort((a, b) -> Double.compare(a.balance, b.balance));

        int left = 0, right = accounts.size() - 1;

        while(left < right) {
            Account receiver = accounts.get(left);
            Account sender = accounts.get(right);

            double need = threshold - receiver.balance;
            double exceed = sender.balance - threshold;

            if (need == exceed) {
                left++;
                right--;
                res.add(new Transaction(sender.id, receiver.id, need));
            } else if (need < exceed) {
                left++;
                sender.balance -= need;
                res.add(new Transaction(sender.id, receiver.id, need));
            } else {
                right--;
                receiver.balance += exceed;
                res.add(new Transaction(sender.id, receiver.id, exceed));
            }
        }
        return res;
    }

    private static void print(List<Transaction> res) {
        for (Transaction trans: res) {
            System.out.println(trans);
        }
    }

    public static void main(String[] args) {

        BalanceAccounts sol = new BalanceAccounts();

        List<Account> test1 = Arrays.asList(
                new Account(0, 200),
                new Account(1, 70),
                new Account(2, 30),
                new Account(3, 90),
                new Account(4, 110)
        );

        List<Account> test2 = Arrays.asList(
                new Account(0, 100),
                new Account(1, 100),
                new Account(2, 100)
        );

        List<Account> test3 = Arrays.asList(
                new Account(0, 300),
                new Account(1, 0),
                new Account(2, 0)
        );

        try {
            System.out.println("==== Test1 ====");
            sol.print(sol.balance(test1, 100));

            System.out.println("==== Test2 ====");
            sol.print(sol.balance(test2, 100));

            System.out.println("==== Test3 ====");
            sol.print(sol.balance(test3, 100));

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}