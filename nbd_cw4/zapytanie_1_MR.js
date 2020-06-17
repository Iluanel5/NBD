var mapHeight  = function(){
	emit(this.sex, this.height);
};

var mapWeight  = function(){
	emit(this.sex, this.weight);
};

var reduceFunction  = function(x, values){
	return Array.avg(values);
};

db.people.find().forEach( function (x) {
	x.height = parseFloat(x.height);
	x.weight = parseFloat(x.weight);
	db.people.save(x);
	});

db.reduceWeight.drop()
db.reduceHeight.drop()

db.people.mapReduce(mapHeight,reduceFunction,{ out: "reduceHeight" })
db.people.mapReduce(mapWeight,reduceFunction,{ out: "reduceWeight" })
printjson(db.reduceWeight.find({}).toArray())
printjson(db.reduceHeight.find({}).toArray())
