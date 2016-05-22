var express = require('express');
var app = express();

var properties = require('./properties/properties_dev.json');

app.use('/lib', express.static(__dirname + '/lib'));
app.use('/styles', express.static(__dirname + '/styles'));
app.use('/scripts', express.static(__dirname + '/scripts'));
app.use('/views', express.static(__dirname + '/views'));

app.get('/', function(req, res) {
  logRequest(req);
  res.sendFile(__dirname + '/index.html');
});

var server = app.listen(properties.Server.port, function() {
  console.log('Server is listening on port: ' + properties.Server.port);
});

function logRequest(req) {
  console.log('REQUEST: %s %s', req.method, req.url);
}
