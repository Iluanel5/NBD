printjson(db.people.find({job: "Editor"}).toArray())
db.people.update({job: "Editor"}, {$unset: {email: 1}},{multi: true})
printjson(db.people.find({job: "Editor"}).toArray())