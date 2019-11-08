from flask import Flask, escape, request
import parsingNaverMap

app = Flask(__name__)

@app.route('/')
def index():
    result = parsingNaverMap.address_system()
    print(result)

    return result