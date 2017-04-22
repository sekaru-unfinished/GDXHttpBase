var express = require('express');
var cors = require('cors');
var bodyParser = require('body-parser');
var low = require('lowdb');
var app = express();

app.use(cors());
app.use(bodyParser.json());

var server = app.listen(3000, function () {
  var port = server.address().port;
  console.log("LD38 Server listening on port " + port);
});

// set defaults
var db = low('db.json');
db.defaults({ lobbies: [], users: [], places: [] }) 
  .write()

app.get('/', function (req, res) {
  
});