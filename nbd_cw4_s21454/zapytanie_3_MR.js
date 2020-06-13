var mapJob = function () {
      emit({ job: this.job }, 1);
};

function reduceJob(id, docs) {
      return Array.sum(docs);
}

db.reduceUniqueJobs.drop()
db.people.mapReduce(mapJob, reduceJob, { out: "reduceUniqueJobs" })
printjson(db.reduceUniqueJobs.find({}).toArray())
