const bodyParser = require('body-parser');
const express = require('express');

const app = express();

app.use(bodyParser.json('application/json'));

// Atsakys į užklausą http://localhost:8080/hello
app.get('/hello', function(req, res, next) {
  res.send({ message: 'Hello, world!' });
});


// Atsakys į POST užklausą http://localhost:8080/add
// Tikisi rasti a ir b parametrus, pvz.: {"a": 1, "b": 2}
app.post('/add', function(req, res, next) {
  if (req.body.a && req.body.b) {
    const a = req.body.a;
    const b = req.body.b;

    res.send({ result: (a+b) });
  } else {
    res.status(400).send({ error: 'Parameters a and b are required' });
  }
});

app.listen(8080, () => console.log('Ready.'));
