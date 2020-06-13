var mapAVG = function () {
    var weight = parseFloat(this.weight);
    var height = parseFloat(this.height);
    var bmi = weight / ((height * height) / 10000);
    var value = { SUM: bmi, count: 1 };
    emit(this.nationality, value);
};

var reduceAVG = function (key, values) {
    var value = { SUM: 0, count: 0 };
    values.forEach(function (val) {
        value.SUM += val.SUM;
        value.count += val.count;
    });

    return value;
};
var finalizeAVG = function (key, reduceVal) {
    reduceVal.avgBMI = reduceVal.SUM / reduceVal.count;
    return reduceVal;
}

var mapMinMax = function () {
    var weight = parseFloat(this.weight);
    var height = parseFloat(this.height);
    var value = weight / ((height * height) / 10000);
    emit(this.nationality, value);
};

var reduceMin = function (key, values) {
    var min = values[0];
    values.forEach(function (val) {
        if (val < min) min = val;
    })
    return min;
};

var reduceMax = function (key, values) {
    var max = values[0];
    values.forEach(function (val) {
        if (val > max) max = val;
    })
    return max;
}

db.reduceBmiMin.drop()
db.reduceBmiMax.drop()
db.reduceBmiAvg.drop()
db.people.mapReduce(mapMinMax, reduceMin, { out: "reduceBmiMin" })
db.people.mapReduce(mapMinMax, reduceMax, { out: "reduceBmiMax" })
db.people.mapReduce(mapAVG, reduceAVG, { out: "reduceBmiAvg", finalize: finalizeAVG })
printjson(db.reduceBmiMin.aggregate([
    {
        $lookup: {
            from: "reduceBmiMax",
            localField: "_id",
            foreignField: "_id",
            as: "Maximum"
        }
    },
    {
        $lookup: {
            from: "reduceBmiAvg",
            localField: "_id",
            foreignField: "_id",
            as: "Average"
        },
    },
    {
        $unwind: "$Maximum"
    },
    {
        $unwind: "$Average"
    }]).toArray())