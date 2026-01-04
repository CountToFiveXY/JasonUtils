package Asphalt9;

import java.util.Arrays;
import java.util.HashMap;

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
        for (int a = 0; a <= getMaxTries(0); a++ ) {
            for (int b = 0; b <= getMaxTries(1); b++ ) {
                for (int c = 0; c <= getMaxTries(2); c++) {
                    for (int d = 0; d <= getMaxTries(3); d++) {
                        for (int e = 0; e <= getMaxTries(4); e++) {
                            for (int f = 0; f <= getMaxTries(5); f++) {
                                for (int g = 0; g <= getMaxTries(6); g++) {
                                    for (int h = 0; h <= getMaxTries(7); h++) {
                                        for (int i = 0; i <= getMaxTries(8); i++) {
                                            int totalSp = a * spList[0] + b * spList[1] + c * spList[2] + d * spList[3] + e * spList[4] + f * spList[5] + g * spList[6] + h * spList[7] + i * spList[8];
                                            int coin = a * coinList[0] + b * coinList[1] + c * coinList[2] + d * coinList[3] + e * coinList[4] + f * coinList[5] + g * coinList[6] + h * coinList[7] + i * coinList[8];
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
        System.out.printf("一共可以获取 %d + %d = %d, 在 %d/%d 时停\n顺序 %s", maxCoinsToStop, maxCoinInList, maxCoinsToStop + maxCoinInList, stopSp, maxSP, Arrays.toString(record.get(maxCoinsToStop)));
    }

    private int[] setSpList () {

        /*
        return new int[] {
                1080, 1620, 2520,
                2340, 3510, 5460,
                6700, 6700, 6700
        };

        return new int[] {
                1800, 2700, 4200,
                3900, 5850, 9100,
                11000, 11000, 11000
        };

        return new int[] {
                1440, 2160, 3360,
                3120, 4680, 7280,
                35000, 35000, 35000
        };

        */

        return new int[] {
                1800, 2700, 4200,
                3900, 5850, 9100,
                37300, 37300, 37300
        };

    }

    private int[] setCoins () {
        int c1 = 90, c2 = 225, c3 = 3000;
        return new int[] {
                3 * c1, 4 * c1, 5 * c1,
                3 * c2, 4 * c2, 5 * c2,
                c3, c3, c3,
        };
    }

    private int setMaxSp() {
        return 37300;
    }

    public static void main(String[] args) {
        DriverSyndicate driverSyndicate = new DriverSyndicate();
        driverSyndicate.getCoins();
    }

    private void printCoin(int[] repeatTimes) {
        StringBuilder sb = new StringBuilder();
        sb.append("关卡选择: ");
        for (int i = 0; i < repeatTimes.length - 1; i++) {
            if (repeatTimes[i] != 0) {
                sb.append(spList[i] + " x " + repeatTimes[i] + "| " );
            }
        }
        sb.setLength(sb.length() - 2);
        sb.append("(最后一把)");
        System.out.println(sb);
    }
}
