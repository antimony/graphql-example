package su.mhl.graphql.example.dataloader;

import org.dataloader.MappedBatchLoader;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import su.mhl.graphql.example.entity.Movie;
import su.mhl.graphql.example.repo.MovieRepository;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

/**
 * @author Mikhail Surin (msurin)
 */
public class MovieByIdLoader implements MappedBatchLoader<Integer, Movie> {

    @Override
    public CompletionStage<Map<Integer, Movie>> load(Set<Integer> keys) {
        return CompletableFuture.supplyAsync(()->
                MovieRepository.byIds(keys).stream().collect(Collectors.toMap(Movie::getId, i -> i)));
    }

    public static WebClientResponseException notFound() {
        return WebClientResponseException.create(404, "Movie not found", null, null, null, null);
    }
}
