package it.tonicminds.authservice;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.WriteResultChecking;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * @author Alfonso Lentini
 */
@Configuration
@EnableMongoRepositories
public class MongoConfig extends AbstractMongoConfiguration {

    @Value("${mongo.host}")
    private String mongoClient;

    @Value("${mongo.db.name}")
    private String dbName;

    @Override
    protected String getDatabaseName() {
        return dbName;
    }

    @Override
    public MongoClient mongo() throws Exception {
        return new MongoClient(mongoClient);
    }

    @Override
    public MongoTemplate mongoTemplate() throws Exception{
//        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongo(), getDatabaseName());
        MongoTemplate template = new MongoTemplate(mongo(),getDatabaseName());
        template.setWriteResultChecking(WriteResultChecking.EXCEPTION);
        return template;
    }

//    @Override
//    @Bean
//    public CustomConversions customConversions() {
//        List<Converter<?, ?>> converters = new ArrayList<>();
//        converters.add(new AuthenticationTokenConverter());
//        return new CustomConversions(converters);
//    }

}
