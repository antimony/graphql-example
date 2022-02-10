package su.mhl.graphql.example.entity;

import java.util.List;
import java.util.Objects;

import io.leangen.graphql.annotations.GraphQLQuery;

/**
 * @author Mikhail Surin (msurin)
 */
public final class ListResult<T> {

    private final List<T> items;

    private final PagingInfo paging;

    /**
     */
    public ListResult(List<T> items, PagingInfo paging) {
        this.items = items;
        this.paging = paging;
    }

    @GraphQLQuery
    public List<T> items() {
        return items;
    }

    @GraphQLQuery
    public PagingInfo paging() {
        return paging;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ListResult) obj;
        return Objects.equals(this.items, that.items) &&
               Objects.equals(this.paging, that.paging);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, paging);
    }

    @Override
    public String toString() {
        return "ListResult[" +
               "items=" + items + ", " +
               "paging=" + paging + ']';
    }


}
