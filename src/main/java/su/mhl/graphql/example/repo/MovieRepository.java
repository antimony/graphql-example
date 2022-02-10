package su.mhl.graphql.example.repo;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import su.mhl.graphql.example.dataloader.MovieByIdLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import su.mhl.graphql.example.entity.Movie;

/**
 * @author Mikhail Surin (msurin)
 */
public class MovieRepository {

    private static final Logger logger = LoggerFactory.getLogger(MovieRepository.class);

    private static final ObjectReader mapper = new ObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .readerFor(Movie.class);

    public static Movie byId(int id) {
        return byIds(Set.of(id)).stream().findFirst()
            .orElseThrow(MovieByIdLoader::notFound);
    }

    public static List<Movie> byIds(Set<Integer> ids) {
       return all().stream().filter(l -> ids.contains(l.getId())).collect(Collectors.toList());
    }

    public static List<Movie> list(int limit, int offset) {
       return all().stream().skip(offset).limit(limit).collect(Collectors.toList());
    }

    public static List<Movie> all() {
        ClassPathResource classPathResource = new ClassPathResource("kp-top.json");
        try {
            logger.info("Чтение файла с фильмами");
            MappingIterator<Movie> objectMappingIterator = mapper.readValues(classPathResource.getInputStream());
            return objectMappingIterator.readAll();
        } catch (IOException e) {
            logger.error("Ошибка чтения файла", e);
            throw new RuntimeException(e);
        }
    }

    public static int total() {
        return all().size();
    }
}
