printjson(db.people.find({"location.city": "Moskwa"}).toArray())
db.people.update({"location.city": "Moscow"}, {$set: {"location.city":"Moskwa"}}, true, true)
printjson(db.people.find({"location.city": "Moskwa"}).toArray())