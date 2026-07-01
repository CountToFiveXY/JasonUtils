package Algorithm.Company.Remitly;

import java.util.*;

/**
 * We are building a money transfer system, Remittancely ©, and our goal is to get our customer’s money
 * to their destination.
 *
 * We offer different types of transfers, economy and express with different delivery deadlines.
 *
 * We can only send one transfer per day.
 *
 * Remittancely receives $10 when a transfer is enqueued but whenever the transfer is late,
 * there is a $3 penalty for the first day, and a $1 penalty for each day afterwards.
 *
 * Build a system that can take in a stream of daily transfers, decide which transfer to disburse
 * for the day, and weigh the tradeoffs you need to make.
 *
 * - Economy
 *   - 2 day deadline (by the day after next)
 *
 * - Express
 *   - 1 day deadline (by the next day)
 *
 * - Penalties for being late (regardless of transfer type)
 *   - $3 for first day miss
 *   - $1 every day after
 *
 * # Clarity
 *
 * In order to mimic a continuous stream of data, we have created sample input in the data below.
 *
 * Each iteration of the loop is one day.
 *
 * On a day you will be given new transfers.
 *
 * For a day you need to decide which transfer to disburse taking into account new transfers
 * and transfers from previous days that have not yet been disbursed.
 *
 * Time is measured in days.
 *
 * If a transfer is created on day D, it can be sent on day D.
 */


public class RemittancelyScheduler {

    enum TransferType {
        EXPRESS, ECONOMY
    }

    static class Transfer {
        String id;
        double amount;
        TransferType type;

        Transfer(String id, double amount, TransferType type) {
            this.id = id;
            this.amount = amount;
            this.type = type;
        }

        public String toString() {
            return id + "(" + type + ", $" + amount + ")";
        }
    }

    public static void main(String[] args) {

        final Transfer[][] allDays = new Transfer[][]{
                {
                        new Transfer("a", 50, TransferType.ECONOMY),
                        new Transfer("b", 20, TransferType.EXPRESS)
                },
                {
                        new Transfer("c", 90, TransferType.EXPRESS),
                        new Transfer("d", 330, TransferType.EXPRESS)
                },
                {
                        new Transfer("e", 780, TransferType.EXPRESS),
                        new Transfer("f", 10, TransferType.ECONOMY)
                }
        };

        //TODO: your code here


    }
}