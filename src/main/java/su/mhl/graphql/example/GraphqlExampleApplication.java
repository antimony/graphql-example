package su.mhl.graphql.example;

import io.leangen.graphql.spqr.spring.autoconfigure.DataLoaderRegistryFactory;
import su.mhl.graphql.example.dataloader.MovieByIdLoader;
import org.dataloader.DataLoader;
import org.dataloader.DataLoaderRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GraphqlExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlExampleApplication.class, args);
    }

    @Bean
    public DataLoaderRegistryFactory dataLoaderRegistryFactory() {
        return () -> new DataLoaderRegistry()
            .register(MovieByIdLoader.class.getSimpleName(),
                DataLoader.newMappedDataLoader(new MovieByIdLoader()));
    }
}
