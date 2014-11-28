/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.tiago.mongodbteste;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.util.JSON;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import pt.tiago.mondodbteste.dto.Adress;
import pt.tiago.mondodbteste.dto.Person;

/**
 *
 * @author NB20708
 */
public class MongoDB {

    private final static String user = "tiago";
    private static final String pass = "tiago";
    private static final String dbName = "contasdespesas";
    private List<Person> personList;
    private MongoClientURI clientURI;
    private MongoClient client;
    private DB db;
    private DBCollection collection;
    private String uri;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MongoDB mongo = new MongoDB();
        try {
            mongo.createConnecntion();
            mongo.populate();
            mongo.converJsonToDBObject();
            mongo.closeConnections();
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(MongoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void converJsonToDBObject() {
        try {
            for (Person person : personList) {
                ObjectMapper mapper = new ObjectMapper();
                String jsonObject = mapper.writeValueAsString(person);
                DBObject dbObject = (DBObject) JSON.parse(jsonObject);
                collection.insert(dbObject);
            }
        } catch (IOException ex) {
            Logger.getLogger(MongoDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void populate() {
        personList = new ArrayList<>();
        Person p1 = new Person();
        p1.setAdress(null);
        p1.setAge(30);
        p1.setName("tiago");
        p1.setWorking(true);
        p1.setWorkingYears(10);
        Adress a1 = new Adress();
        a1.setCity("lisboa");
        a1.setCountry("portugal");
        a1.setHouseNumber(91);
        a1.setStreet("avenida das couves");
        a1.setZip("2710-731");
        p1.setAdress(a1);

        Person p2 = new Person();
        p2.setAdress(null);
        p2.setAge(30);
        p2.setName("jota");
        p2.setWorking(true);
        p2.setWorkingYears(10);
        Adress a2 = new Adress();
        a2.setCity("lisboa");
        a2.setCountry("portugal");
        a2.setHouseNumber(91);
        a2.setStreet("avenida das couves");
        a2.setZip("2710-731");
        p2.setAdress(a2);

        Person p3 = new Person();
        p3.setAdress(null);
        p3.setAge(30);
        p3.setName("filipe");
        p3.setWorking(true);
        p3.setWorkingYears(10);
        Adress a3 = new Adress();
        a3.setCity("lisboa");
        a3.setCountry("portugal");
        a3.setHouseNumber(91);
        a3.setStreet("avenida das couves");
        a3.setZip("2710-731");
        p1.setAdress(a3);

        personList.add(p1);
        personList.add(p2);
        personList.add(p3);
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
        collection = db.getCollection("houses");
    }

    private void closeConnections() {
        //uncomment this statement to drop collection
        //collection.drop();
        //uncomment this statement to remove all documents from collection
        //collection.remove(new BasicDBObject());
        client.close();
    }

}
