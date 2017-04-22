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
db.defaults({ users: [], map: {} }) 
  .write();

app.post('/user', function (req, res) {
  // TODO duplicates
  // db.get('users')
  //   .push({name: req.body.name.value})
  //   .write();

  console.log("Added user: " + req.body.name.value);

  var ip = req.headers['x-forwarded-for'] || 
           req.connection.remoteAddress || 
           req.socket.remoteAddress ||
           req.connection.socket.remoteAddress;

  res.json({head: "login_accept", name: req.body.name.value, ip: ip});
});