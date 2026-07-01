package Algorithm.Company.Sofi;

import java.util.*;

/**
 * ============================================================
 * Problem: GIF Pagination by Tag
 * ============================================================
 *
 * You are given a list of strings representing GIF metadata.
 *
 * The list has a FIXED order:
 *   Every 3 strings represent ONE GIF:
 *     - index i     : gifId
 *     - index i + 1 : gifName
 *     - index i + 2 : gifTag
 *
 * Example:
 *   ["id1", "name1", "funny",
 *    "id2", "name2", "sports",
 *    "id3", "name3", "funny"]
 *
 * Task:
 *   Implement a function that:
 *     1) Deduplicates GIFs
 *        - Deduplication should be based on gifId
 *     2) Filters GIFs by a given tag
 *     3) Applies pagination on the filtered result
 *     4) Returns the GIFs for the requested page
 *
 * Inputs:
 *   - List<String> gifData
 *   - String targetTag
 *   - int page       (1-based index)
 *   - int pageSize
 *
 * Output:
 *   - List<Gif> representing the GIFs on that page
 *
 * Notes:
 *   - If page is out of range, return an empty list
 *   - Time complexity should be O(N)
 *   - Order of GIFs should respect the original input order
 *
 * ============================================================
 */
public class GifPaginationService {

    /**
     * Data model representing a GIF.
     */
    static class Gif {
        String id;
        String name;
        String tag;

        Gif(String id, String name, String tag) {
            this.id = id;
            this.name = name;
            this.tag = tag;
        }

        @Override
        public String toString() {
            return "Gif{id='" + id + "', name='" + name + "', tag='" + tag + "'}";
        }
    }

    /**
     * Returns paginated GIFs filtered by tag after deduplication.
     *
     * @param gifData   raw input list, every 3 strings represent one GIF
     * @param targetTag tag to filter by
     * @param page      page number (1-based)
     * @param pageSize  number of items per page
     * @return          list of GIFs for the requested page
     */
    public List<Gif> getGifsByTagAndPage(
            List<String> gifData,
            String targetTag,
            int page,
            int pageSize) {

        List<Gif> res = new ArrayList<>();

        if (gifData == null || gifData.size() % 3 != 0) {
            return res;
        }

        Set<String> set = new HashSet<>();
        List<Gif> list = new ArrayList<>();

        // TODO:

        //dedup and filter by tag
        for (int i  = 0 ; i < gifData.size() - 2; i += 3) {
            String id = gifData.get(i);
            String name = gifData.get(i+1);
            String tag = gifData.get(i+2);
            if (!set.contains(id)) {
                set.add(id);
                if (tag.equals(targetTag)) {
                    list.add(new Gif(id, name, tag));
                }
            }
        }

        //pagination

        if (list.isEmpty() || pageSize == 0) {
            return res;
        }

        int startIndex = pageSize * page, endIndex = Math.min(list.size(), pageSize * (page + 1));

        //find target page

        for (int i  = startIndex; i < endIndex; i++) {
            res.add(list.get(i));
        }
        return res;
    }

    // ============================================================
    // Test Cases (NO solution logic used here)
    // ============================================================
    public static void main(String[] args) {

        GifPaginationService solver = new GifPaginationService();

        List<String> gifData = Arrays.asList(
                "id1", "gif1", "funny",
                "id2", "gif2", "sports",
                "id3", "gif3", "funny",
                "id1", "gif1_dup", "funny", // duplicate id
                "id4", "gif4", "funny"
        );

        // ------------------------------------------------------------
        // Test 1: Basic filter + dedup + pagination (page 1)
        // ------------------------------------------------------------
        List<Gif> page1 = solver.getGifsByTagAndPage(gifData, "funny", 1, 2);
        assert page1.size() == 2 : "Expected 2 gifs on page 1";
        assert page1.get(0).id.equals("id1");
        assert page1.get(1).id.equals("id3");

        // ------------------------------------------------------------
        // Test 2: Page 2
        // ------------------------------------------------------------
        List<Gif> page2 = solver.getGifsByTagAndPage(gifData, "funny", 2, 2);
        assert page2.size() == 1 : "Expected 1 gif on page 2";
        assert page2.get(0).id.equals("id4");

        // ------------------------------------------------------------
        // Test 3: Page out of range
        // ------------------------------------------------------------
        List<Gif> page3 = solver.getGifsByTagAndPage(gifData, "funny", 3, 2);
        assert page3.isEmpty() : "Expected empty list for out-of-range page";

        // ------------------------------------------------------------
        // Test 4: Tag not found
        // ------------------------------------------------------------
        List<Gif> none = solver.getGifsByTagAndPage(gifData, "nonexistent", 1, 2);
        assert none.isEmpty() : "Expected empty list when tag does not exist";

        // ------------------------------------------------------------
        // Test 5: All duplicates
        // ------------------------------------------------------------
        List<String> dupOnly = Arrays.asList(
                "id1", "gif1", "funny",
                "id1", "gif1_dup", "funny",
                "id1", "gif1_dup2", "funny"
        );
        List<Gif> dedup = solver.getGifsByTagAndPage(dupOnly, "funny", 1, 5);
        assert dedup.size() == 1 : "Expected only 1 gif after dedup";
        assert dedup.get(0).id.equals("id1");

        // ------------------------------------------------------------
        // Test 6: pageSize larger than result size
        // ------------------------------------------------------------
        List<Gif> largePage = solver.getGifsByTagAndPage(gifData, "sports", 1, 10);
        assert largePage.size() == 1 : "Expected 1 gif when pageSize is large";
        assert largePage.get(0).id.equals("id2");

        // ------------------------------------------------------------
        // Test 7: Invalid page or pageSize
        // ------------------------------------------------------------
        assert solver.getGifsByTagAndPage(gifData, "funny", 0, 2).isEmpty();
        assert solver.getGifsByTagAndPage(gifData, "funny", 1, 0).isEmpty();

        System.out.println("✅ All assertions passed.");
    }
}
