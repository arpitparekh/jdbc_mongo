package com.jdbc.my_mongo;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class App 
{
	private static MongoClient mongoClient;
    private static MongoDatabase mongoDatabase;
    
    public static void main( String[] args )
    {
    	mongoClient = MongoClients.create("mongodb://localhost:27017");
        
    	mongoDatabase = mongoClient.getDatabase("yuvraj_batch2");
    	
    	System.out.println("Connected to MongoDB");
    	
    	mongoClient.close();
    	
    }
}
