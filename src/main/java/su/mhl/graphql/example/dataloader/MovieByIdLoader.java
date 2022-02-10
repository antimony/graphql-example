package su.mhl.graphql.example.dataloader;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

import org.dataloader.MappedBatchLoader;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import su.mhl.graphql.example.entity.Movie;
import su.mhl.graphql.example.repo.MovieRepository;

/**
 * @author Mikhail Surin (msurin)
 */
public class MovieByIdLoader implements MappedBatchLoader<Integer, Movie> {

    @Override
    public CompletionStage<Map<Integer, Movie>> load(Set<Integer> keys) {
        var list = MovieRepository.byIds(keys).stream().collect(Collectors.toMap(Movie::getId, i -> i));
        return CompletableFuture.completedFuture(list);
    }

    public static WebClientResponseException notFound() {
        return WebClientResponseException.create(404, "Movie not found", null, null, null, null);
    }
}
