from flask import Flask, request, render_template, redirect, url_for
import requests
import json

app = Flask(__name__)

@app.route('/')
def index():
    days = ['mon', 'tue', 'wed', 'thu' , 'fri', 'sat', 'sun']
    return render_template('index.html', days=days)

@app.route('/webtoon/<day>')
def day_webtoon_list(day):
    url = f'http://webtoon.daum.net/data/pc/webtoon/list_serialized/{day}'
    response = requests.get(url)
    data = response.json()
    webtoons = {}

    for webtoon in data['data']:
        webtoon_title = webtoon['title']
        webtoon_nickname = webtoon['nickname']
        webtoon_introduction = webtoon['introduction']
        
        webtoon_artists = []

        for artist in webtoon['cartoon']['artists']:
            webtoon_artists.append(artist['name'])

        webtoons[webtoon_title] = [webtoon_nickname, webtoon_introduction, ", ".join(webtoon_artists)]

    return render_template('day_webtoon_list.html', webtoon_dic=webtoons)

@app.route('/webtoon/<nickname>')
def webtoon_info(nickname):
    url = f'http://webtoon.daum.net/data/pc/webtoon/view/{nickname}'
    response = requests.get(url)

    return response.json()

@app.route('/naver')
def fake_naver():
    return render_template('naver.html')

@app.route('/naver/search')
def fake_naver_search():
    query = request.args.get('query')

    return render_template('search.html', query=query)

@app.route('/main')
def main():
    return '로그인에 성공하셨습니다. 메인페이지 입니당ㅎㅎ'

@app.route('/login')
def login():
    return render_template('login.html')

@app.route('/login/submit', methods=['POST'])
def login_submit():
    return redirect(url_for('main'))