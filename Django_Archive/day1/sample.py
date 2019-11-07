import requests

url = "http://webtoon.daum.net/data/pc/webtoon/list_serialized/fri"
response = requests.get(url)
print(response.text)
