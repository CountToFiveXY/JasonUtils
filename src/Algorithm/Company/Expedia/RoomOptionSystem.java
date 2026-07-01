package Algorithm.Company.Expedia;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoomOptionSystem {

    class RoomOption {
        int price;
        List<String> features;
        int availability;

        public RoomOption(int price, List<String> features, int availability) {
            this.price = price;
            this.features = features;
            this.availability = availability;
        }
    }

    public List<RoomOption> listAllOption(int start,
                                          int end,
                                          List<String> features,
                                          Map<Integer, List<RoomOption>> timeToRooms) {
        /*/

        give a start date,and end date,
        give all the roomOption with prices being total price, merge their features and return min availablity
        sample input:
        {
            175: [120, [parking, tv], 3],
            176: [120, [parking, tv], 2],
            177: [130, [parking], 4], [140, [ tv], 3],
            178: [180, [parking, tv], 1],[190, [parking, tv, swim], 2]
        }

        call this method (176, 178, parking, input)
        output input:
            [120  + 130 + 180, [parking, tv], 1]
            [120  + 130 + 190, [parking, tv, swim], 2]
         */

        List<RoomOption> res = new ArrayList<>();
        return res;
    }
}
