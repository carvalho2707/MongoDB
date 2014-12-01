/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.tiago.mongodbteste;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.util.JSON;
import de.undercouch.bson4jackson.types.ObjectId;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import pt.tiago.mondodbteste.dto.Category;
import pt.tiago.mondodbteste.dto.Person;
import pt.tiago.mondodbteste.dto.Purchase;

/**
 *
 * @author NB20708
 */
public class MongoDB {

    private final static String user = "tiago";
    private static final String pass = "tiago";
    private static final String dbName = "contasdespesas";
    private List<Person> personList;
    private List<Category> categoryList;
    private List<Purchase> purchaseList;
    private MongoClientURI clientURI;
    private MongoClient client;
    private DB db;
    private List<DBCollection> collection;
    private String uri;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MongoDB mongo = new MongoDB();
        try {
            mongo.createConnecntion();
            mongo.setPersonList(Populator.populatePerson());
            mongo.setCategoryList(Populator.populateCategory());
            mongo.setPurchaseList(Populator.populatePurchase());
            mongo.converJsonToDBObject();
            mongo.search();
            mongo.closeConnections();

        } catch (UnknownHostException ex) {
            Logger.getLogger(MongoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public MongoDB() {
        this.collection = new ArrayList<>();
    }

    public void converJsonToDBObject() {
        try {
            for (Category category : categoryList) {
                ObjectMapper mapper = new ObjectMapper();
                String jsonObject = mapper.writeValueAsString(category);
                DBObject dbObject = (DBObject) JSON.parse(jsonObject);
                collection.get(0).insert(dbObject);
            }
            for (Person person : personList) {
                ObjectMapper mapper = new ObjectMapper();
                String jsonObject = mapper.writeValueAsString(person);
                DBObject dbObject = (DBObject) JSON.parse(jsonObject);
                collection.get(1).insert(dbObject);
            }
            for (Purchase purchase : purchaseList) {
                ObjectMapper mapper = new ObjectMapper();
                String jsonObject = mapper.writeValueAsString(purchase);
                DBObject dbObject = (DBObject) JSON.parse(jsonObject);
                collection.get(2).insert(dbObject);
            }
        } catch (IOException ex) {
            Logger.getLogger(MongoDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void createConnecntion() throws UnknownHostException {
        StringBuilder str = new StringBuilder();
        str.append("mongodb://");
        str.append(user);
        str.append(":");
        str.append(pass);
        str.append("@ds055690.mongolab.com:55690/");
        str.append(dbName);
        uri = str.toString();
        clientURI = new MongoClientURI(uri);
        client = new MongoClient(clientURI);
        db = client.getDB(clientURI.getDatabase());
        collection.add(db.getCollection("Category"));
        collection.add(db.getCollection("Person"));
        collection.add(db.getCollection("Purchase"));
        //uncomment this statement to remove all documents from collection
        for (DBCollection collectionDB : collection) {
            collectionDB.remove(new BasicDBObject());
        }
    }

    private void closeConnections() {
        //uncomment this statement to drop collection
        //collection.drop();
        client.close();
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Purchase> getPurchaseList() {
        return purchaseList;
    }

    public void setPurchaseList(List<Purchase> purchaseList) {
        this.purchaseList = purchaseList;
    }

    private void search() {
        //search all... select * from
        DBCursor cursor = collection.get(1).find();
        while (cursor.hasNext()) {
            DBObject obj = cursor.next();
            BasicDBObject basicObj = (BasicDBObject) obj;
            Person person = new Person();
            person.setID(String.valueOf(basicObj.getObjectId("_id")));
            person.setName(basicObj.getString("name"));
            person.setSurname(basicObj.getString("surname"));
            System.out.println(person.toString());
        }
        
        //Select from Person where name = Tiago
        System.out.println("------------------------------------------------------------------------");
        BasicDBObject basicObj = new BasicDBObject("name","Tiago");
        cursor = collection.get(1).find(basicObj);
        while(cursor.hasNext()){
            System.out.println(cursor.next());
        }
        
        //Select from Person where name = Tiago and surname = Carvalho
        System.out.println("------------------------------------------------------------------------");
        basicObj = new BasicDBObject("name","Tiago").append("surname", "Carvalho");
        cursor = collection.get(1).find(basicObj);
        while(cursor.hasNext()){
            System.out.println(cursor.next());
        }
        
        //Select * from Person where name = Tiago and surname = Erro
        System.out.println("------------------------------------------------------------------------");
        basicObj = new BasicDBObject("name","Tiago").append("surname", "Erro");
        cursor = collection.get(1).find(basicObj);
        while(cursor.hasNext()){
            System.out.println(cursor.next());
        }
        
        //SELECT * FROM PERSON WHERE surname like '%arval%'
        System.out.println("------------------------------------------------------------------------");
        basicObj = new BasicDBObject("surname",  java.util.regex.Pattern.compile("arval"));
        cursor = collection.get(1).find(basicObj);
        while(cursor.hasNext()){
            System.out.println(cursor.next());
        }
        //SELECT * FROM PERSON WHERE surname like '%arval%'
        System.out.println("------------------------------------------------------------------------");
        basicObj = new BasicDBObject();
        basicObj.put("surname",  java.util.regex.Pattern.compile("arval"));
        cursor = collection.get(1).find(basicObj);
        while(cursor.hasNext()){
            System.out.println(cursor.next());
        }
    }

}
