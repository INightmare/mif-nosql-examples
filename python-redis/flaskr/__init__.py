import re
import werkzeug
import redis
from flask import (Flask, request, jsonify, abort)

licenseRegex = "^[A-Z0-9]{1,7}$"

def create_app():
    app = Flask(__name__)
    redisClient = redis.Redis(host='localhost', port=6379, decode_responses=True)

    def locationKey(licenseNo):
        return f'Vehicle:{licenseNo}'

    @app.route('/vehicle/location/<license>', methods=['GET'])
    def get_location(license):
        if re.search(licenseRegex, license) != None:
            location = redisClient.get(locationKey(license))

            if (location != None):
                locations = location.split(':')

                return {
                    "lat": float(locations[0]),
                    "lng": float(locations[1])
                }
            else:
                return { "message": "Car not found" }, 404
        else:
            return { "message": "Invalid license plate number" }, 400

    @app.route('/vehicle/location/<license>', methods=['POST'])
    def update_location(license):
        if re.search(licenseRegex, license) != None:
            reqBody = request.json
            carLocationValue = str(reqBody.get("lat")) + ":" + str(reqBody.get("lng"))
            redisClient.set(locationKey(license), carLocationValue)

            return ''
        else:
            return { "message": "Invalid license plate number" }, 404

    @app.route('/vehicle/location/<license>', methods=['DELETE'])
    def delete_location(license):
        if re.search(licenseRegex, license) != None:
            redisClient.delete(locationKey(license))

            return ''
        else:
            return { "message": "Invalid license plate number" }, 404

    return app

