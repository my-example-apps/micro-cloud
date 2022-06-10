package com.example.microcloud;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.apache.commons.net.util.SubnetUtils;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.web.util.matcher.IpAddressMatcher;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

//@EnableEurekaClient
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.microcloud.repo")
public class ResourceServerApplication {

    public static void main(String[] args) {

        MongoClient mongoClient = ResourceServerApplication.mongoClient();

            MongoDatabase database = mongoClient.getDatabase("admin");
            MongoCollection<Document> collection = database.getCollection("onlineSessions");
        MongoCollection<Document> mongoCollection = mongoClient.getDatabase("admin").getCollection("onlineSessions");

        Document document = mongoCollection.find(Filters.and(Filters.eq("username", "admin"),
                Filters.eq("deleted", false),
                Filters.gt("expireAt", new Date())
        )).first();
        System.out.println(document);

    }
    public static MongoClient mongoClient() {
        String uri = "mongodb://admin:admin123@192.168.25.182:27017/admin";
        ConnectionString connectionString
                = new ConnectionString(uri);
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }
    public static void main2(String[] args) {
        String[] subnetsMasks =new String[] {"192.168.107.1/24","192.168.103.1/24"  };
        Collection<SubnetUtils.SubnetInfo> subnets = new ArrayList<SubnetUtils.SubnetInfo>();
        for (String subnetMask : subnetsMasks) {
            subnets.add(new SubnetUtils(subnetMask).getInfo());
        }
        IpAddressMatcher ipAddressMatcher = new IpAddressMatcher("192.168.103.1/24");

        String ipAddress = "192.168.103.128";
        System.out.println("matches >> "+ipAddressMatcher.matches(ipAddress));
        for (SubnetUtils.SubnetInfo subnet : subnets) {
            if (subnet.isInRange(ipAddress)) {
                System.out.println("IP Address " + ipAddress + " is in range " + subnet.getCidrSignature());
            }
        }
        //   SpringApplication.run(ResourceServerApplication.class, args);
        /*Refill refill = Refill.greedy(10, Duration.ofSeconds(1));
        Bandwidth limit = Bandwidth.classic(10, refill);
        Bucket bucket = Bucket.builder().addLimit(limit).build();
        for (int i = 1; i <= 20; i++) {
            try {
                Thread.sleep(200);
                if (bucket.tryConsume(1)) {
                    System.out.println("success consume token << " + i);
                } else {
                    System.out.println("filed consume token << " + i);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            }
*/

    }

}
