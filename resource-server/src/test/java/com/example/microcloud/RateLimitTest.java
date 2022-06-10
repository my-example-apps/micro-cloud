package com.example.microcloud;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static com.mongodb.client.model.Filters.eq;

@SpringBootTest
public class RateLimitTest {

    @Test
    public void simpleTest(){
        String uri = "mongodb+srv://admin:admin123@192.168.25.182/admin";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("admin");
            MongoCollection<Document> collection = database.getCollection("onlineSessions");
            Document doc = collection.find(Filters.eq("_id", "6214f3f9e17bdf3a000fee1a")).first();
            System.out.println(doc.toJson());
        }
    }
}
