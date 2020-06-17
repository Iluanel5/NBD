var mapCurrency = function () {
	for (var card in this.credit) {
		var currency = this.credit[card].currency;
		var balance = parseFloat(this.credit[card].balance);
		emit(currency, balance);
	}
};

function reduceCurrency(id, docs) {
	return Array.sum(docs);
}

db.reduceTotalBalance.drop()
db.people.mapReduce(mapCurrency, reduceCurrency, { out: "reduceTotalBalance" })
printjson(db.reduceTotalBalance.find({}).toArray())