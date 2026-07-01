package Algorithm.Company.Remitly;
import java.util.*;

public class StockTrade {
    class StockPrice {
        String timestamp;
        Double price;

        public StockPrice(String timestamp, Double price) {
            this.timestamp = timestamp;
            this.price = price;
        }
    }

    class Trade {
        String buytime;
        String selltime;

        public Trade(String buytime,  String selltime) {
            this.buytime = buytime;
            this.selltime = selltime;
        }

        public String toString() {
            return "buytime: " + buytime + "selltime: " + selltime;
        }
    }

    public Trade maxProfit(Map<String, Double> map) {
        List<StockPrice> prices = new ArrayList<>();
        for (Map.Entry<String, Double> entry: map.entrySet()) {
            prices.add(new StockPrice(entry.getKey(), entry.getValue()));
        }

        prices.sort((a, b) -> a.timestamp.compareTo(b.timestamp));

        Trade trade  = null;
        String buytime = null;
        Double hold = null;

        double maxProfit = 0;

        for (int i = 0; i < prices.size(); i++) {
            StockPrice curr = prices.get(i);
            if (hold == null) {
                buytime = curr.timestamp;
                hold = curr.price;
                continue;
            }
            if (curr.price > hold) {
                double profit = curr.price - hold;

                if (profit > maxProfit) {
                    maxProfit = profit;
                    trade = new Trade(buytime, curr.timestamp);
                }
            } else if (curr.price < hold) {
                buytime = curr.timestamp;
                hold = curr.price;
            }
        }

        return trade;
    }


    public static void main(String[] args) {
        StockTrade s = new StockTrade();

        Map<String, Double> map = new HashMap<>();

        // Normal
        map.put("2024-01-03 10:00", 120.0);
        map.put("2024-01-01 10:00", 100.0);
        map.put("2024-01-02 10:00", 150.0);

        System.out.println(s.maxProfit(map));

        // Edge:
        // all descending

        // Edge:
        // single item

        // Edge:
        // duplicate timestamp
    }
}