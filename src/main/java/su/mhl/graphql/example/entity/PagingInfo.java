package su.mhl.graphql.example.entity;

import java.util.Objects;

import io.leangen.graphql.annotations.GraphQLQuery;

/**
 * @author Mikhail Surin (msurin)
 */
public final class PagingInfo {

    private final int limit;

    private final int offset;

    private final int total;

    /**
     */
    public PagingInfo(int limit, int offset, int total) {
        this.limit = limit;
        this.offset = offset;
        this.total = total;
    }

    @GraphQLQuery
    public int limit() {
        return limit;
    }

    @GraphQLQuery
    public int offset() {
        return offset;
    }

    @GraphQLQuery
    public int total() {
        return total;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (PagingInfo) obj;
        return this.limit == that.limit &&
               this.offset == that.offset &&
               this.total == that.total;
    }

    @Override
    public int hashCode() {
        return Objects.hash(limit, offset, total);
    }

    @Override
    public String toString() {
        return "PagingInfo[" +
               "limit=" + limit + ", " +
               "offset=" + offset + ", " +
               "total=" + total + ']';
    }


}
