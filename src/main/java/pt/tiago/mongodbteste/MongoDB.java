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
            mongo.setPersonList(Populator.populate());
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

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
    

}
