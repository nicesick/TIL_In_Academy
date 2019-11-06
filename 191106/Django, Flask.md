# Django, Flask

## I. 파이썬 기반 프레임워크

- Flask 를 먼저 배운다.
- 크롤링 하는 법을 먼저 배운다.



## II. 브라우저 렌더링 과정

- https://d2.naver.com/helloworld/59361



## III. Wappalyzer

- 웹 분석 확장 프로그램



## IV. Web 분석

- 네이버 웹툰 > 개발자 모드 > Network > index.nhn
- Response 를 선택 (Preview 를 하면 미리보기도 가능!)
- head > og 태그 : 카카오 같은 걸로 링크 보내면 미리보기 설명
  - 잘 엔진이 서치할 수 있도록! > 서치 엔진에 최적화
- 보통 페이지는 기본 틀만 주고,
- 로드된 이후에 요청을 통해 값을 받아온다!
  - 따라서 크롤링은 xml, json 을 파싱해야 제대로 된 값을 받아 올 수 있다.
  - graphql 이라는 것을 이용해서 데이터를 가지고 올 때도 있다.
  - 보통 데이터를 물고 있는 것들은 XHR 탭에 있다.



## V. 파이썬 설치

- python-3.7.4
- vscode
- powershell
  - vscode 로 파이썬 코드 작성하고
  - powershell 에서 코드를 실행시킨다.



## VI. XHR 파일 받아보기

- 타겟 XHR 파일의 Header부분을 봐서 Request URL 을 복사
- requests module download
  - pip install requests

```python
import requests

url = "http://webtoon.daum.net/data/pc/webtoon/list_serialized/wed"
response = requests.get(url)
print(response)

# 이렇게 실행하면 response 200 가 뜬다.

print(response.text)
```

- 만약 POST 방식이고, 쿠키조건도 있고 나중에는 받아올 수도 있고, 없을 수도 있다.
  - requests.post 함수를 써서 헤더에 필요한 정보를 추가할 수 있다.
  - Cookie, Host, Referer, User-agant
  - 필요한 것들을 담아가면서 요청해보자!

```python
import requests

url = "https://ticket.melon.com/offer/ajax/offerList.json?offerPosType=MAIN_B_CO_1"

headers = {
    'User-Agent' : 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36'
}

response = requests.get(url, headers=headers)
print(response.text)

# 처음에 406 에러가 뜬 상황
# 헤더로 조건을 추가해 줬더니 받아올 수 있었다!
```

