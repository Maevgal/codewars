package kuy5;

import java.util.Collections;
import java.util.List;

/*https://www.codewars.com/kata/515bb423de843ea99400000a*/

public class PaginationHelper<I> {
    private final List<I> collection;
    private final int itemsPerPage;

    public PaginationHelper(List<I> collection, int itemsPerPage) {
        this.collection = collection == null ? Collections.<I>emptyList() : collection;
        this.itemsPerPage = itemsPerPage;
    }

    public int itemCount() {
        return collection.size();
    }

    public int pageCount() {
        return itemsPerPage == 0 ? 0 : (int) Math.ceil(itemCount() * 1.00 / itemsPerPage);
    }

    public int pageItemCount(int pageIndex) {
        if (pageIndex < 0 || pageIndex > pageCount() - 1) {
            return -1;
        }
        return itemCount() - pageIndex * itemsPerPage > itemsPerPage ? itemsPerPage : itemCount() - pageIndex * itemsPerPage;

    }

    public int pageIndex(int itemIndex) {
        if (itemIndex < 0 || itemIndex > itemCount() - 1) {
            return -1;
        }
        return itemsPerPage == 0 ? -1 : itemIndex / itemsPerPage;
    }
}
