package Algorithm.Company.Sofi;

import java.util.*;

public class GifPaginationService {

    // Data model
    static class Gif {
        String id;
        String name;
        String tag;

        Gif(String id, String name, String tag) {
            this.id = id;
            this.name = name;
            this.tag = tag;
        }
    }

    /**
     * @param input     List of strings: [id, name, tag, id, name, tag, ...]
     * @param targetTag tag to filter
     * @param page      page number (1-based)
     * @param pageSize  number of gifs per page
     * @return          list of Gif info for the given page
     */
    public List<Gif> getGifsByTagAndPage(
            List<String> input,
            String targetTag,
            int page,
            int pageSize) {

        return Collections.emptyList();
    }
}
