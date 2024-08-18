package Asphalt9;

import java.util.*;

public class DriverSyndicate {

    int[] spList = setSpList();
    int[] coinList = setCoins();
    int maxSP = setMaxSp();

    private int getMaxTries(int i) {
        if (coinList[i] == 0) {
            return 0;
        }
        return maxSP/ spList[i];
    }

    public void getCoins() {
        HashMap<Integer, int[]> record = new HashMap<>();
        int maxCoinsToStop = 0;
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
                                            totalSp = a * spList[0] + b * spList[1] + c * spList[2] + d * spList[3] + e * spList[4] + f * spList[5] + g * spList[6] + h * spList[7] + i * spList[8];
                                            coin = a * coinList[0] + b * coinList[1] + c * coinList[2] + d * coinList[3] + e * coinList[4] + f * coinList[5] + g * coinList[6] + h * coinList[7] + i * coinList[8];
                                            if (totalSp < maxSP && coin >= maxCoinsToStop) {
                                                maxCoinsToStop = coin;
                                                int[] repeatTimes = new int[] {a, b, c, d, e, f, g, h, i};
                                                record.put(maxCoinsToStop, repeatTimes);
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
        for (int i = 0; i < spList.length; i++) {
            stopSp += spList[i] * record.get(maxCoinsToStop)[i];
        }

        //add max coin as last step
        int maxCoinInList = Arrays.stream(coinList).max().getAsInt();
        int maxCoinIndexInList = Arrays.stream(coinList).boxed().toList().indexOf(maxCoinInList);
        // add 1 repeat times to the maxCoinInList for last run
        record.get(maxCoinsToStop)[maxCoinIndexInList] += 1;

        printCoin(record.get(maxCoinsToStop));
        System.out.printf("max coin is %d + %d = %d, sequence %s, stop at %d%n", maxCoinsToStop, maxCoinInList, maxCoinsToStop + maxCoinInList, Arrays.toString(record.get(maxCoinsToStop)), stopSp);
    }

    private int setMaxSp() {
        return 33000;
    }

    private int[] setSpList () {
        return new int[] {
                2700, 4050, 6300,
                4500, 6750, 10500,
                0, 0, 0
        };
    }

    private int[] setCoins () {
        //300, 240, 180, 0, 0, 0, 0, 0, 0
        return new int[] {
                0, 800, 0,
                2100, 3200,
                3500, 0, 0, 0
        };
    }

    private void printCoin(int[] repeatTimes) {
        StringBuilder sb = new StringBuilder();
        sb.append("SP repeat time: ");
        for (int i = 0; i < repeatTimes.length; i++) {
            if (repeatTimes[i] != 0) {
                sb.append(spList[i] + " * " + repeatTimes[i] + "/" );
            }
        }
        System.out.println(sb);
    }
}
