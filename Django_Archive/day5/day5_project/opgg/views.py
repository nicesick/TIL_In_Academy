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

    result = {}

    if tier_info == None:
        result = {
            'nickname' : 'undefined'
        }
    else:
        rank_type = tier_info.select_one('.sub-tier__rank-type').text
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