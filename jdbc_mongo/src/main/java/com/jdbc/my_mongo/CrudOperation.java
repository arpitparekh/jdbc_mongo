package com.jdbc.my_mongo;

import java.util.ArrayList;

import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class CrudOperation {
	
	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private MongoCursor<Document> cursor;
	private MongoCollection<Document> docCollection;
	
	private static final String DATABASE = "yuvraj_batch";
	private static final String URL = "mongodb://localhost:27017";
	private static final String COLLECTION = "student";
	
	public CrudOperation(String url,String database,String collection){
		
		mongoClient = MongoClients.create(url);
		mongoDatabase = mongoClient.getDatabase(database);
		docCollection = mongoDatabase.getCollection(collection);
		
	}
	
	public void insertData(int id,String name,float height) {
		
		Document document = new Document("id",id).append("name", name).append("height", height);
		docCollection.insertOne(document);
		System.out.println("Document added Successfully");
	}
	
	public ArrayList<Document> readDocument() {
		
		cursor = docCollection.find().iterator();
		ArrayList<Document> docList = new ArrayList<Document>(); 
		while(cursor.hasNext()) {
			docList.add(cursor.next());
		}
		return docList;
	}
	
	public void closeCollection() {
		mongoClient.close();
	}

	public static void main(String[] args) {
		
		CrudOperation crud = new CrudOperation(URL,DATABASE,COLLECTION);
		
//		crud.insertData(3, "Yash", 99.12f); // used for inserting data
		
		System.out.println(crud.readDocument());
		
		crud.closeCollection();
		
	}

}
