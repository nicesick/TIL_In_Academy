import requests

url = "https://ticket.melon.com/offer/ajax/offerList.json?offerPosType=MAIN_B_CO_1"
headers = {
    'User-Agent' : 'Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.87 Safari/537.36'
}

response = requests.get(url, headers=headers)
print(response.text)