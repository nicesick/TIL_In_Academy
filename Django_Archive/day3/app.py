from flask import Flask, request, render_template
import requests

app = Flask(__name__)

if __name__ == '__main__':
    app.run(debug= True)

@app.route('/')
def index():
    #request.args는 클라이언트로 부터 받은 파라미터를 저장하고 있는 친구
    #request.args.get('파라미터명')
    #request.args -> dict 타입(Immutable)). request.args[파라미터명]
    student = request.args.get('student')

    return{'hello': student}

@app.route('/daum_webtoon')
def daum_toon_index():
    days = ["mon", "tue", "wed", "thu", "fri", "sat", "sun"]

    return render_template('daum_webtoon_list.html', **locals())

@app.route('/daum_webtoon/<day>')
def daum_toon(day):
 
    url = f"http://webtoon.daum.net/data/pc/webtoon/list_serialized/{day}"
    data = request_json_data_from_url(url)
    return { day: parse_daum_webtoon_data(data) }
  
def request_json_data_from_url(url):
    response= requests.get(url)
    data = response.json()
    return data

def parse_daum_webtoon_data(data):
    toons = []
    for toon in data["data"]:
        #제목의 key 는 title
        title = toon["title"]
        #설명의 key는 introduction
        desc = toon["introduction"]


        #장르의 위치는 'cartoon'안에 'genre'라는 리스트 안에 'name'이라는 key
        genres = []
        for genre in toon["cartoon"]["genres"]:
            genres.append(genre["name"])

        artist = []
        for author in toon["cartoon"]["artists"]:
            artist.append(author["name"])

        img_url = toon["pcThumbnailImage"]["url"]
    
        tmp = {
            title : {
                "desc" : desc,
                "author" : artist,
                "img_url" : img_url
            }
        }
        toons.append(tmp)
    return toons    