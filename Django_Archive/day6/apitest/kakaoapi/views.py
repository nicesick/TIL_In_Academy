from django.shortcuts import render
import requests
import json

# Create your views here.

def main(request):
    return render(request, 'kakao_main.html')

def find_address(request):
    address = request.GET['address']
    api_key = '138290ea2446c4d77965e8fdf0f84dc5'

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
    api_key = '138290ea2446c4d77965e8fdf0f84dc5'

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