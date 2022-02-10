package su.mhl.graphql.example.service;

import io.leangen.graphql.annotations.GraphQLEnvironment;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.execution.ResolutionEnvironment;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import su.mhl.graphql.example.dataloader.MovieByIdLoader;
import org.dataloader.DataLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import su.mhl.graphql.example.entity.LazyListResult;
import su.mhl.graphql.example.entity.LazyPagingInfo;
import su.mhl.graphql.example.entity.ListResult;
import su.mhl.graphql.example.entity.Movie;
import su.mhl.graphql.example.entity.PagingInfo;
import su.mhl.graphql.example.repo.MovieRepository;

/**
 * @author Mikhail Surin (msurin)
 */
@Component
@GraphQLApi
public class MovieService {

    private static final Logger logger = LoggerFactory.getLogger(MovieService.class);

    @GraphQLQuery
    public Mono<ListResult<Movie>> movies(int offset, int limit) {
        return Mono.just(new ListResult<>(MovieRepository.list(limit, offset),
            new PagingInfo(limit, offset, MovieRepository.total())));
    }

    @GraphQLQuery
    public Mono<LazyListResult<Movie>> moviesLazy(int offset, int limit) {
        return Mono.just(new LazyListResult<>(MovieRepository.list(limit, offset),
            new LazyPagingInfo(limit, offset, MovieRepository::total)));
    }

    @GraphQLQuery
    public Mono<Movie> movie(int id,
        @GraphQLEnvironment ResolutionEnvironment env)
    {
        return Mono.fromSupplier(() -> MovieRepository.byId(id));
    }

    @GraphQLQuery
    public Mono<Movie> movieBatch(int id,
        @GraphQLEnvironment ResolutionEnvironment env)
    {
        DataLoader<Integer, Movie> dataLoader =
            env.dataFetchingEnvironment.getDataLoader(MovieByIdLoader.class.getSimpleName());
        return Mono.fromFuture(dataLoader.load(id))
            .switchIfEmpty(Mono.defer(() -> Mono.error(MovieByIdLoader.notFound())));
    }
}
