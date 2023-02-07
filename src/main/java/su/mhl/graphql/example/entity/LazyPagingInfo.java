package su.mhl.graphql.example.entity;

import io.leangen.graphql.annotations.GraphQLQuery;

import java.util.Objects;
import java.util.function.IntSupplier;

/**
 * @author Mikhail Surin (msurin)
 */
public final class LazyPagingInfo {

    private final int limit;

    private final int offset;

    private final IntSupplier totalFunc;

    /**
     *
     */
    public LazyPagingInfo(int limit, int offset, IntSupplier totalFunc) {
        this.limit = limit;
        this.offset = offset;
        this.totalFunc = totalFunc;
    }

    @GraphQLQuery
    public int getTotal() {
        return totalFunc.getAsInt();
    }

    @GraphQLQuery
    public int limit() {
        return limit;
    }

    @GraphQLQuery
    public int offset() {
        return offset;
    }

    public IntSupplier totalFunc() {
        return totalFunc;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (LazyPagingInfo) obj;
        return this.limit == that.limit &&
                this.offset == that.offset &&
                Objects.equals(this.totalFunc, that.totalFunc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(limit, offset, totalFunc);
    }

    @Override
    public String toString() {
        return "LazyPagingInfo[" +
                "limit=" + limit + ", " +
                "offset=" + offset + ", " +
                "totalFunc=" + totalFunc + ']';
    }

}
