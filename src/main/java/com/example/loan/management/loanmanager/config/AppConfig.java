package com.example.loan.management.loanmanager.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import cz.jirutka.spring.embedmongo.EmbeddedMongoBuilder;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AppConfig {
    private static final String MONGO_DB_URL = "localhost";
    private static final String MONGO_DB_NAME = "embeded_db";

    /*@Bean
    public MongoTemplate mongoTemplate() throws IOException {
        final EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();

        mongo.setBindIp(MONGO_DB_URL);

        final MongoClient mongoClient = mongo.getObject();
        final MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, MONGO_DB_NAME);
        return mongoTemplate;
    }*/

    public Mongo mongo() throws IOException {
        return new EmbeddedMongoBuilder()
                .bindIp(MONGO_DB_URL)
                .build();
    }
}
