# DJango 로또 번호 생성기

## I. 기본 세팅

- settings
  - INSTALLED_APPS 에 추가

```python
INSTALLED_APPS = [
    'lotto',
    'django.contrib.admin',
    'django.contrib.auth',
    'django.contrib.contenttypes',
    'django.contrib.sessions',
    'django.contrib.messages',
    'django.contrib.staticfiles',
]
```

- urls
  - lotto 경로 import (두 파일의 위치가 다르기 때문에!!)
  - 경로 꽂아주기

```python
from lotto import views

urlpatterns = [
    path('admin/', admin.site.urls),
    path('lotto/', views.lotto),
    path('lotto/winning/', views.winning)

# 어떤 주소로 받을 지 > 받았을 때 어떤 함수를 실행 시킬지
]
```

- views
  - 함수 선언
  - views.py 가 있는 곳에서 templates 폴더 만들기
  - templates 에서 해당 html 만들어주기

```python
def lotto(request):
# request 가 꼭 첫번째로 들어가야만 한다!
    return render(request, 'lotto.html')

def winning(request):
    return render(request, 'winning.html')
```

- lotto

  - views.py

  ```python
  from django.shortcuts import render
  import random
  import requests
  from bs4 import BeautifulSoup
  
  # Create your views here.
  
  def lotto(request):
      return render(request, 'lotto.html')
  
  def winning(request):
      num_list = list(range(1,46))
      num_count = request.GET['count']
  
      result = random.sample(num_list, int(num_count))
      result.sort()
  
      url = 'https://dhlottery.co.kr/gameResult.do?method=byWin'
      response = requests.get(url)
  
      html = BeautifulSoup(response.text, 'html.parser')
      winning_numbers = html.select('div.win span')
  
      winning_count = 0
      winning_list = []
  
      for number in winning_numbers:
          # result list 변수에 number가 포함되어 있는지
          winning_list.append(int(number.text))
          if int(number.text) in result: # element가 list에 들어갔있는지 check
              winning_count += 1
  
      return render(request,'winning.html', {'result': result, 'winning_list': winning_list, 'winning_count': winning_count})
  ```

  

  - lotto.html

  ```html
  <!DOCTYPE html>
  <html lang="ko">
  <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <meta http-equiv="X-UA-Compatible" content="ie=edge">
      <title>Lotto</title>
  </head>
  <body>
      <h1>몇 개의 번호를 추첨하시겠습니까앗</h1>
      <form action="winning" method="GET">
          <select name="count">
              <option value="1">1개</option>
              <option value="2">2개</option>
              <option value="3">3개</option>
              <option value="4">4개</option>
              <option value="5">5개</option>
              <option value="6">6개</option>
          </select>
          <input type="submit">
      </form>
  </body>
  </html>
  ```

  

  - winning.html

  ```html
  <!DOCTYPE html>
  <html lang="ko">
  <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <meta http-equiv="X-UA-Compatible" content="ie=edge">
      <title>WINNING</title>
  </head>
  <body>
      {% for num in result %}
          <span>{{ num }}</span>
      {% endfor %}
  
      <h1>
      {% for num in winning_list %}
          <span>{{ num }}</span>
      {% endfor %}
      </h1>
  
      {{ winning_count }}
  </body>
  </html>
  ```



## ASCII ART

- http://artii.herokuapp.com/make?text=hello&font=bubble__
  - 자동으로 hello 가 나온닷
- http://artii.herokuapp.com/fonts_list



## Html 에서 이중 딕셔너리 접근

- result.name 처럼 접근하면 됨!!

- op.gg

```python
from django.shortcuts import render
import requests
from bs4 import BeautifulSoup

# Create your views here.

def opgg(request):
    return render(request, 'opgg.html')

def result(request):
    summoner = request.GET['summoner']
    
    url = f'https://www.op.gg/summoner/userName={summoner}'
    response = requests.get(url)

    html = BeautifulSoup(response.text, 'html.parser')
    tier_info = html.select_one('div.sub-tier')

    rank_type = tier_info.select_one('.sub-tier__rank-type').text

    result = {}

    if rank_type == None:
        result = {
            'nickname' : 'undefined'
        }
    else:
        rank = tier_info.select_one('.sub-tier__rank-tier').text
        win_rates = tier_info.select('.sub-tier__gray-text')

        win_rate = []

        for rate in win_rates:
            sampledRate = rate.text.replace("\n", " ")
            sampledRate = sampledRate.replace("/", " ")
            win_rate.append(sampledRate.strip())

        result = {
            'nickname' : summoner,
            'rank_type' : rank_type,
            'rank' : rank,
            'win_rate' : win_rate
        }

    return render(request, 'opgg_result.html', result)
```

- opgg.html

```html
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>OPGG</title>
</head>
<body>
    <form action="result">
        <h1>소환사 명</h1>
        <input type="text" name="summoner">
        <input type="submit">
    </form>
</body>
</html>
```

- opgg_result.html

```html
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>OPGG RESULT</title>
</head>
<body>
    {% if nickname != 'undefined' %}
        <h1>{{ nickname }}</h1>
        <h1>{{ rank_type }}</h1>
        <h1>{{ rank }}</h1>
        <h1>{{ win_rate }}</h1>
    {% else %}
        <h1>{{ nickname }}</h1>
    {% endif %}
</body>
</html>
```

