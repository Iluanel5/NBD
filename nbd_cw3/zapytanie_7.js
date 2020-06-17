printjson(db.people.find({height: {$gt: "190"}}).toArray());
 
try {
   db.people.deleteMany({height: {$gt: "190"}})
} catch (e) {
   print (e);
}

printjson(db.people.find({height: {$gt: "190"}}).toArray());