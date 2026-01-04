package Algorithm;

import java.util.*;

public class codetest {

    static List<List<Integer>> result = new ArrayList<>();
    static Stack<Integer> prices = new Stack<>();
    static Stack<Integer> span = new Stack<>();

    public static void main(String[] args) {
        String[] input = {"root/qgjazhtliq/djmevsktisuvd/acsuolhnermqzok/mkts/ibrdqxawjgut/emb wl.txt(odumfqzwczehoyk) " +
                "z.txt(gojsklotgchjzfm) txtoyg.txt(gojsklotgchjzfm) eupidhefx.txt(ahlsazuzrsf) " +
                "rekzkaifwp.txt(yfxaymkefaofowqjpgaceffkjsehtmqkgy) l.txt(odumfqzwczehoyk) bqmhpxumxlbe.txt(yfxaymkefaofowqjpgaceffkjsehtmqkgy) " +
                "qoqgiauqbayuc.txt(odumfqzwczehoyk) mpstemqlxy.txt(ahlsazuzrsf)"};
        System.out.println(findDuplicate(input));
    }



        public static List<List<String>> findDuplicate(String[] paths) {
            //content to path mapping
            List<List<String>> res = new ArrayList<>();
            HashMap<String, List<String>> map = new HashMap<>();

            for (String path: paths) {
                String[] s = path.split(" ");
                String root = s[0];

                for (int i = 1; i < s.length; i++) {
                    String[] file = s[i].split("txt");
                    String fileName = file[0] + "txt";
                    String content = file[1];
                    map.computeIfAbsent(content, k -> new ArrayList<>()).add(root + "/" + fileName);
                }
            }

            for (List<String> list: map.values()) {
                if (list.size() > 1) {
                    res.add(list);
                }
            }

            return res;
        }

}
