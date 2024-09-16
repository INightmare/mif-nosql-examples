const bodyParser = require('body-parser');
const express = require('express');
const { createClient } = require('redis');

const app = express();
const redisClient = createClient();

app.use(bodyParser.json('application/json'));

const carLicenseNoRegex = /^[A-Z0-9]{1,7}$/

app.get('/vehicle/location/:license', async function(req, res) {
  const license = req.params.license;

  if (license.match(carLicenseNoRegex)) {
    const location = await redisClient.get(locationKey(license));

    if (location != null) {
      const locationComponents = location.split(':');
      res.send({
        lat: Number(locationComponents[0]),
        lng: Number(locationComponents[1])
      });
    } else {
      res.status(404).send();
    }
    
  } else {
    respondWithInvalidLicenseError(res);
  }
});

app.post('/vehicle/location/:license', async function(req, res) {
  const license = req.params.license;

  if (license.match(carLicenseNoRegex)) {
    await redisClient.set(locationKey(license), `${req.body.lat}:${req.body.lng}`);
    res.send();
  } else {
    respondWithInvalidLicenseError(res);
  }
});

app.delete('/vehicle/location/:license', async function(req, res) {
  const license = req.params.license;

  if (license.match(carLicenseNoRegex)) {
    await redisClient.del(locationKey(license));
    res.send();
  } else {
    respondWithInvalidLicenseError(res);
  }
});

function locationKey(licenseNo) {
  return `Vehicle:${licenseNo}`;
}


function respondWithInvalidLicenseError(res) {
  res.status(400).send({ message: "Invalid license plate number provided."});
}

// Connect to the database.
// The default address is localhost:6379
// If the address is different (for example it's a remote Redis),
// you need to specify connection parameters.


app.listen(8080, async () => {
  await redisClient.connect();
  console.log('Ready.')
});
