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
import java.net.UnknownHostException;

/**
 *
 * @author NB20708
 */
public class MongoDB {

    private final static String user = "tiago";
    private static final String pass = "tiago";
    private static final String dbName = "contasdespesas";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException {
        // TODO code application logic here

        StringBuilder str = new StringBuilder();
        str.append("mongodb://");
        str.append(user);
        str.append(":");
        str.append(pass);
        str.append("@ds055690.mongolab.com:55690/");
        str.append(dbName);

        final BasicDBObject[] seedData = createSeedData();

        MongoClientURI uri = new MongoClientURI(str.toString());

        MongoClient client = new MongoClient(uri);

        DB db = client.getDB(uri.getDatabase());

        DBCollection songs = db.getCollection("songs");

        songs.insert(seedData);
        
        BasicDBObject updateQuery = new BasicDBObject("song", "One Sweet Day");
        songs.update(updateQuery, new BasicDBObject("$set", new BasicDBObject("artist", "Mariah Carey ft. Boyz II Men")));
        
        /*
         * Finally we run a query which returns all the hits that spent 10 
         * or more weeks at number 1.
         */
      
        BasicDBObject findQuery = new BasicDBObject("weeksAtOne", new BasicDBObject("$gte",10));
        BasicDBObject orderBy = new BasicDBObject("decade", 1);

        DBCursor docs = songs.find(findQuery).sort(orderBy);

        while(docs.hasNext()){
            DBObject doc = docs.next();
            System.out.println(
                "In the " + doc.get("decade") + ", " + doc.get("song") + 
                " by " + doc.get("artist") + " topped the charts for " + 
                doc.get("weeksAtOne") + " straight weeks."
            );
        }

        client.close();
    }

    public static BasicDBObject[] createSeedData() {

        BasicDBObject seventies = new BasicDBObject();
        seventies.put("decade", "1970s");
        seventies.put("artist", "Debby Boone");
        seventies.put("song", "You Light Up My Life");
        seventies.put("weeksAtOne", 10);

        BasicDBObject eighties = new BasicDBObject();
        eighties.put("decade", "1980s");
        eighties.put("artist", "Olivia Newton-John");
        eighties.put("song", "Physical");
        eighties.put("weeksAtOne", 10);

        BasicDBObject nineties = new BasicDBObject();
        nineties.put("decade", "1990s");
        nineties.put("artist", "Mariah Carey");
        nineties.put("song", "One Sweet Day");
        nineties.put("weeksAtOne", 16);

        final BasicDBObject[] seedData = {seventies, eighties, nineties};

        return seedData;
    }

}
