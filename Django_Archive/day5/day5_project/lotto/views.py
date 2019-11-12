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