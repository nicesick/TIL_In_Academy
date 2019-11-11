# Django

## I. 일단 Summary

- CSRF 토큰? : 토큰을 비교해서 일반 사용자인지 안 좋은 사용자 인지 비교할 수 있도록 했음
  - input hidden 으로 구성되어 있음
  - 파라미터로 같이 넘겨주자!! (POST 방식으로)
- FLASK > DJANGO
  - FLASK : 경량화 버전
  - DJANGO : 중규모 버전



## II. 다음 웹툰 리스트 뿌려주고, 웹툰 세부내용 받기

```python
# app.py

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

# 이에 대한 결과값은 templates 안에 html 로 넣어주었당

```



## III. API STORE

- 여기서 api 같은 거 써서 해도 좋아



## IV. form 형식 전달

```python
@app.route('/naver')
def fake_naver():
    return render_template('naver.html')

@app.route('/naver/search')
def fake_naver_search():
    query = request.args.get('query')
# query 라는 이름으로 form 데이터가 넘어왔다면
# 이렇게 받아주기만 하면 돼!!

    return render_template('search.html', query=query)
```



## V. REST API

- 서로 간의 약속! (두 가지 약속!)
  - URI는 정보의 자원을 표현해야 한다.
  - 자원에 대한 행위는 HTTP Method (GET, POST, PUT, DELETE)로 표현한다.
  - CRUD
    - C : POST
    - R : GET
    - U : PUT
    - D : DELETE
- 가짜 로그인, 메인 페이지를 만들어 보자!

```python
from flask import Flask, request, render_template, redirect, url_for
# 이번엔 redirect, url_for 가 추가되었다

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

@app.route('/login/submit')
def login_submit():
    return redirect(url_for('main'))
# 이게 라우팅 주소가 아니라 함수이름일수도??
```

- POST 방식
  - 포스트 방식은? > 값들이 헤더에 숨겨져서 들어간다!
  - 근데...

```html
Method Not Allowed
The method is not allowed for the requested URL.
<!-- @app.route 에 포스트를 지정해주어야 한다! -->

<!-- @app.route('/login/submit', methods=['POST']) --
```

- 만약 POST 방식이 view 를 가지게 된다면?
- 그리고 F5 버튼을 눌렀다면?

```python
@app.route('/login/submit', methods=['POST'])
def login_submit():
    return render_template('success.html')
```

```html
찾고 있는 페이지에서 사용자가 입력한 정보를 사용했습니다. 해당 페이지로 돌아가면 기존 작업을 반복할 수 있습니다. 계속하시겠습니까?
```

- View 를 가지는 것은 GET 방식만 가지고 있어야 한다!
  - 나머지 POST, PUT, DELETE 형식은 Redirect 시켜줘야 한다!



## VI. MVVM

- Model, View, ViewAndModel 방식이 대세닸!



## VII. DJango 시작

- pip install django

- django-admin startproject myapp
  - 하위 디렉토리로 들어가면 미리 구성된 프로젝트를 볼 수 있다!

- python manage.py runserver
- localhost:8000
- python manage.py migrate



## VIII. DJango 구조

- manage.py : 여기부터 코드가 시작됨 (많이 수정되진 않음)
- wsgi.py (web server gateway interface) : 서비스 배포할 때 사용되는 부분
- settings.py : 기본 설정 파일
  - SECRET_KEY
  - INSTALLED_APPS : 각 Application 을 등록하는 공간
    - 등록을 해야만 라우팅 해준다!
  - DATABASES : 기본은 sqlite3 로 되어있다.
  - LANGUAGE_CODE
    - en-us > ko
  - TIME_ZONE
    - UTC > Asia/seoul



- Project 구조 : 프로젝트는 여러 Application 으로 이루어져 있다
  - Application (ex. 로그인 기능) : 각 기능이 Application 으로 명명된다
  - Application (ex. 웹툰 리스트 기능)
  - ...
- urls.py : 모든 url patterns 들은 여기서 관리된다.
  - 라우팅이 여기서 실행되는 것!!



- 필요한 controller 생성
  - python manage.py startapp webtoon
  - views.py 에서 필요한 로직들이 구현된다.
  - models.py 에서 필요한 model 들을 가져온다.



## X. Request_Response_Flows

![request_response_flows](https://user-images.githubusercontent.com/4971222/68567483-70acbf00-049c-11ea-90cb-8161d8270b3b.jpg)

- 개발자가 되기 위해선 이런 구조를 꼭 알아놔야 한닷!