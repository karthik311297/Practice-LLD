package lld.filesystem.search;

import lld.filesystem.search.filter.SearchCondition;
import lld.filesystem.search.filter.SearchFilter;

import java.util.List;

public class SearchOption {
    private List<SearchFilter> searchFilters;
    private SearchCondition searchCondition;

    public SearchOption(List<SearchFilter> searchFilters, SearchCondition searchCondition) {
        this.searchFilters = searchFilters;
        this.searchCondition = searchCondition;
    }

    public List<SearchFilter> getSearchFilters() {
        return searchFilters;
    }

    public SearchCondition getSearchCondition() {
        return searchCondition;
    }
}
