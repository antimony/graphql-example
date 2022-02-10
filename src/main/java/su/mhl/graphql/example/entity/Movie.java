package su.mhl.graphql.example.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.leangen.graphql.annotations.GraphQLQuery;

/**
 * @author Mikhail Surin (msurin)
 */
@JsonIgnoreProperties(allowGetters = true)
public final class Movie {

    private int id;

    private String title;

    private String rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
