from bs4 import BeautifulSoup
import requests

url = 'http://www.saramin.co.kr/zf_user/jobs/list/job-category?cat_cd=404&tab_type=all&panel_type=&search_optional_item=n&search_done=y&panel_count=y&smart_tag='
response = requests.get(url)
html = BeautifulSoup(response.text, 'html.parser')

companies = html.select('ul.product_list li')

for company in companies:
    idx = company.select_one('a')['href'].split('=')[-1]
    company_info_url = 'http://www.saramin.co.kr/zf_user/jobs/relay/view-ajax'
    company_info_params = {
        'rec_idx' : idx
    }

    company_response = requests.post(company_info_url, params=company_info_params)
    company_html = BeautifulSoup(company_response.text, 'html.parser')

    break