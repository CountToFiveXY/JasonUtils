package Asphalt9;

import java.util.*;

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
        int totalSp;
        int coin;
        for (int a = 0; a <= getMaxTries(0); a++ ) {
            for (int b = 0; b <= getMaxTries(1); b++ ) {
                for (int c = 0; c <= getMaxTries(2); c++) {
                    for (int d = 0; d <= getMaxTries(3); d++) {
                        for (int e = 0; e <= getMaxTries(4); e++) {
                            for (int f = 0; f <= getMaxTries(5); f++) {
                                for (int g = 0; g <= getMaxTries(6); g++) {
                                    for (int h = 0; h <= getMaxTries(7); h++) {
                                        for (int i = 0; i <= getMaxTries(8); i++) {
                                            totalSp = a * sp[0] + b * sp[1] + c * sp[2] + d * sp[3] + e * sp[4] + f * sp[5] + g * sp[6] + h * sp[7] + i * sp[8];
                                            coin = a * coins[0] + b * coins[1] + c * coins[2] + d * coins[3] + e * coins[4] + f * coins[5] + g * coins[6] + h * coins[7] + i * coins[8];
                                            if (totalSp < maxSP && coin >= maxCoin) {
                                                maxCoin = coin;
                                                record.put(maxCoin, new int[] {a, b, c, d, e, f, g, h, i});
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        int stopSp = 0;
        for (int i = 0; i < sp.length; i++) {
            stopSp += sp[i] * record.get(maxCoin)[i];
        }

        //add max coin as last step
        int maxCoinInList = Arrays.stream(coins).max().getAsInt();
        int maxCoinIndexInList = Arrays.stream(coins).boxed().toList().indexOf(maxCoinInList);
        record.get(maxCoin)[maxCoinIndexInList] += 1;

        System.out.printf("max coin is %d + %d = %d, sequence %s, stop at %d%n", maxCoin, maxCoinInList, maxCoin + maxCoinInList, Arrays.toString(record.get(maxCoin)), stopSp);
    }

    private int setMaxSp() {
        return 30000;
    }

    private int[] setSpList () {
        return new int[] {
                1959, 1260, 840, 4200, 2700, 1800, 7000, 4500, 3000
        };
    }

    private int[] setCoins () {
        //300, 240, 180, 0, 0, 0, 0, 0, 0
        return new int[] {
                270, 216, 162, 675, 540, 405, 0, 0, 0
        };
    }
}
