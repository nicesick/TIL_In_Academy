# kakao api, naver api

- https://developers.kakao.com/docs/restapi/local

- API 문서를 볼 때 주의할 점
  - 요청방식(GET, POST)
  - 요청을 보내야 할 주소 (End-point)
  - 필수적인 파라미터 파악 (query=)
  - 인증키를 어떻게 보내야 하는지

```python
from django.shortcuts import render
import requests
import json

# Create your views here.

def main(request):
    return render(request, 'kakao_main.html')

def find_address(request):
    address = request.GET['address']
    api_key = 'Your REST API Key'

    url = f'https://dapi.kakao.com/v2/local/search/address.json'

    params = {
        'query' : address,
        'size' : 30
    }

    headers = {
        'Authorization' : f'KakaoAK {api_key}'
    }

    response = requests.get(url, params=params, headers=headers)
    json = response.json()

    context = {
        'positions' : json['documents']
    }

    return render(request, 'kakao_address.html', context)

def keyword_result(request):
    api_key = 'Your REST API Key'

    keyword = request.GET['keyword']
    position = request.GET['position']

    gps_x = position.split('&')[0].split('=')[1]
    gps_y = position.split('&')[1].split('=')[1]

    url = 'https://dapi.kakao.com/v2/local/search/keyword.json'

    params = {
        'query' : keyword,
        'x' : gps_x,
        'y' : gps_y
    }

    headers = {
        'Authorization' : f'KakaoAK {api_key}'
    }

    response = requests.get(url, params=params, headers=headers)

    context = {
        'result' : response.json()['documents']
    }

    return render(request, 'kakao_result.html', context)
```

- kakao_main.html

```html
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>KAKAO MAIN</title>
</head>
<body>
    <form action="address">
        <input type="text" name="address">
        <input type="submit" value="Search">
    </form>
</body>
</html>
```

- kakao_address.html

```html
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>KAKAO ADDRESS</title>
</head>
<body>
    <form action="result">
        <input type="text" name="keyword">
        <input type="submit" value="키워드 검색">

        {% for position in positions %}
            <h5><label>{{ position.address_name }}<input type="radio" name="position" value="x={{ position.x }}&y={{ position.y }}"></label></h5>
        {% endfor %}
    </form>
</body>
</html>
```

- kakao_result.html

```html
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>KAKAO RESULT</title>
</head>
<body>
    {% for site in result %}
        <p>상호명 : {{ site.place_name }}</p>
        <p>주소 : {{ site.address_name }}</p>
        <p>도로명주소 : {{ site.road_address_name }}</p>
        <a href="{{ site.place_url }}">상세정보보기</a>
    {% endfor %}
</body>
</html>
```

