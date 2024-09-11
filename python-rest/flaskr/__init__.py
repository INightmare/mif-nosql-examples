import werkzeug
from flask import (Flask, request, jsonify)

def create_app():
    app = Flask(__name__)

    @app.route('/hello', methods=['GET'])
    def hello():
        return { "message": "Hello, world!" }

    @app.route('/add', methods=['POST'])
    def add():
        body = request.get_json()
        a = body["a"]
        b = body["b"]

        return { "result": a + b }


    return app
