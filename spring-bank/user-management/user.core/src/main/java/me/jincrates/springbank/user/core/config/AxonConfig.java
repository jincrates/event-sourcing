package me.jincrates.springbank.user.core.config;

import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import java.util.Collections;
import org.axonframework.eventhandling.tokenstore.TokenStore;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.extensions.mongo.DefaultMongoTemplate;
import org.axonframework.extensions.mongo.MongoTemplate;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoFactory;
import org.axonframework.extensions.mongo.eventsourcing.eventstore.MongoSettingsFactory;
import org.axonframework.extensions.mongo.eventsourcing.tokenstore.MongoTokenStore;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfig {
    @Value("${spring.data.mongo.host:127.0.0.1}")
    private String mongoHost;
    @Value("${spring.data.mongo.port:27017}")
    private int mongoPort;
    @Value("${spring.data.mongo.database:user}")
    private String mongoDatabase;

    @Bean
    public MongoClient mongo() {
        var mongoFactory = new MongoFactory();
        var mongoSettingsFactory = new MongoSettingsFactory();
        mongoSettingsFactory.setMongoAddresses(Collections.singletonList(new ServerAddress(mongoHost, mongoPort)));
        mongoFactory.setMongoClientSettings(mongoSettingsFactory.createMongoClientSettings());

        return mongoFactory.createMongo();
    }

    @Bean
    public MongoTemplate axonMongoTemplate(MongoClient mongoClient) {
        return DefaultMongoTemplate.builder()
            .mongoDatabase(mongoClient, mongoDatabase)
            .build();
    }

    @Bean
    public TokenStore tokenStore(Serializer serializer, MongoTemplate mongoTemplate) {
        return MongoTokenStore.builder()
            .mongoTemplate(mongoTemplate)
            .serializer(serializer)
            .build();
    }

    @Bean
    public Serializer serializer() {
        return JacksonSerializer.defaultSerializer();
    }

    @Bean
    public EventStorageEngine storageEngine(MongoTemplate mongoTemplate) {
        return MongoEventStorageEngine.builder()
            .mongoTemplate(mongoTemplate)
            .build();
    }

    @Bean
    public EmbeddedEventStore eventStore(EventStorageEngine storageEngine) {
        return EmbeddedEventStore.builder()
            .storageEngine(storageEngine)
            .build();
    }
}
