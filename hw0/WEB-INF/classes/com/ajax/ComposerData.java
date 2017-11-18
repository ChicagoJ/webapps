package com.ajax;

import java.util.HashMap;
import com.mongodb.*;

/**
 *
 * @author nbuser
 */
public class ComposerData {
    
    private HashMap composers = new HashMap();
    

    public HashMap getComposers() {
        return composers;
    }
    
    public ComposerData() {
        MongoClient mongo;
        mongo = new MongoClient("localhost",27017);
        
        DB db = mongo.getDB("a1");
        DBCollection item = db.getCollection("myItems");
        
        BasicDBObject searchQuery = new BasicDBObject();
        DBCursor cursor = item.find(searchQuery);
        
        while (cursor.hasNext()) {
            BasicDBObject obj = (BasicDBObject) cursor.next();
            composers.put(obj.getString("_id"), new Composer(obj.getString("_id"), obj.getString("itemName"), obj.getString("itemPrice"), obj.getString("productClass")));
        }
    }

}
