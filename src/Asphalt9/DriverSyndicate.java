package Asphalt9;

import java.util.*;
import java.util.stream.Collectors;

public class DriverSyndicate {

    int[] sp = setSpList();
    int[] coins = setCoins();

    int maxSP = setMaxSp();

    private int getMaxTries(int i) {
        if (coins[i] == 0) {
            return 1;
        }
        return maxSP/sp[i];
    }
    public void getCoins() {
        HashMap<Integer, int[]> record = new HashMap<>();
        int maxCoin = 0;
        int totalSp = 0;
        int coin = 0;
        for (int a = 0; a < getMaxTries(0); a++ ) {
            for (int b = 0; b < getMaxTries(1); b++ ) {
                for (int c = 0; c < getMaxTries(2); c++) {
                    for (int d = 0; d < getMaxTries(3); d++) {
                        for (int e = 0; e < getMaxTries(4); e++) {
                            for (int f = 0; f < getMaxTries(5); f++) {
                                totalSp = a * sp[0] + b * sp[1] + c * sp[2] + d * sp[3] + e * sp[4] + f * sp[5];
                                coin = a * coins[0] + b * coins[1] + c * coins[2] + d * coins[3] + e * coins[4] + f * coins[5];
                                if (totalSp < maxSP && coin >= maxCoin) {
                                    maxCoin = coin;
                                    record.put(maxCoin, new int[] {a, b, c, d, e, f});
                                }
                            }
                        }
                    }
                }
            }
        }
        int lastCoin = Arrays.stream(coins).max().getAsInt();
        int lastCoinIndex = Arrays.stream(coins).boxed().toList().indexOf(lastCoin);
        record.get(maxCoin)[lastCoinIndex] += 1;
        System.out.printf("max coin is %d + %d = %d, sequence %s%n", maxCoin, lastCoin, maxCoin + lastCoin, Arrays.toString(record.get(maxCoin)));
    }

    private int setMaxSp() {
        return 20000;
    }

    private int[] setSpList () {
        return new int[] {
                3150, 2025, 1350, 5250, 3375, 2250
        };
    }

    private int[] setCoins () {
        return new int[] {
                400, 320, 240, 0, 0, 0
        };
    }
}
