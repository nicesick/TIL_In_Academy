import requests
import json

def request_json_data_from_url(url):
    response = requests.get(url)
    return response.json()

def parse_daum_webtoon_data(data):
    toons = []

    for toon in data["data"]:
        title = toon["title"]
        desc = toon["introduction"]
        writer = []

        for artist in toon["cartoon"]["artists"]:
            writer.append(artist["name"])
        
        genres = []
        
        for genre in toon["cartoon"]["genres"]:
            genres.append(genre["name"])

        img_url = toon["pcThumbnailImage"]["url"]

        toon = {
            title : {
                "desc" : desc,
                "writer" : writer,
                "genre" : genres,
                "img_url" : img_url
            }
        }

        toons.append(toon)

    return toons

week = ["mon", "tue", "wed", "thu", "fri", "sat", "sun"]
url = "http://webtoon.daum.net/data/pc/webtoon/list_serialized/"

weeksToons= {}

for day in week:
    targetUrl = url + day
    data = request_json_data_from_url(targetUrl)

    weeksToons[day] = parse_daum_webtoon_data(data)

print(weeksToons)