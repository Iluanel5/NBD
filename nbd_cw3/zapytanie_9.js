printjson(db.people.find({first_name: "Antonio"}).toArray())
db.people.update({first_name: "Antonio"}, {$set: {"hobby":"ping pong"}}, false, true)
printjson(db.people.find({first_name: "Antonio"}).toArray())