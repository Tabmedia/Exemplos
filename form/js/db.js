
var db;

function initDB(){

	db = openDatabase('tab_ex_db', '1.0', 'Tab Exemplo DB', 5 * 1024 * 1024);

	db.transaction(function (tx) {  
	   tx.executeSql('CREATE TABLE IF NOT EXISTS POSTS (id INTEGER PRIMARY KEY, json TEXT)');
	});
}

function insert(json, onSuccess, onError){

	if(db == undefined)
		initDB()

	db.transaction(function (tx) {  
	   tx.executeSql('INSERT INTO POSTS (json) VALUES (?)', [json]);
	},onError, onSuccess);
}

function getPendingItems(callback){

	if(db == undefined)
		initDB()

	db.transaction(function (tx) {
		   tx.executeSql('SELECT * FROM POSTS', [], callback, null);
		});
}


function deleteItem(id){

	if(db == undefined)
		initDB()
	
	db.transaction(function (tx) {
		   tx.executeSql('DELETE FROM POSTS WHERE id = ?', [id], null, null);
		});
}