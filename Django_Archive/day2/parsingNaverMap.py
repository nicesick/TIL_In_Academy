import requests
import json

def request_data_from_url(url):
    response = requests.get(url)
    return response.json()

def parse_naver_address_data_for_latLng(targetAddress, data):
    site = data["result"]["site"]["list"][0]
    
    lat = site["x"]
    lng = site["y"]
    name = site["name"]
    
    return {
        "lat" : lat,
        "lng" : lng,
        "name" : name
    }

def parse_naver_map_route_latlng(data):
    route_latlng = []

    for route in data["routes"]:
        step_latlng = []

        for leg in route["legs"]:
            for step in leg["steps"]:
                if step["path"] != "":
                    step_latlng.append(step["path"])

        route_latlng.append(step_latlng)

    return route_latlng

startAddress = "홍대입구역"
endAddress = "멀티캠퍼스"

url = 'https://m.map.naver.com/apis/search/poi?query='

start_point_info = request_data_from_url(url + startAddress)
start_point_latlng = parse_naver_address_data_for_latLng(startAddress, start_point_info)

end_point_info = request_data_from_url(url + endAddress)
end_point_latlng = parse_naver_address_data_for_latLng(endAddress, end_point_info)

start_point_latlng["name"] = start_point_latlng["name"].replace(' ', '+')
end_point_latlng["name"] = end_point_latlng["name"].replace(' ', '+')

print(start_point_latlng)
print(end_point_latlng)

url = 'https://m.map.naver.com/spirra/findCarRoute.nhn?route=route3&output=json&result=web3&coord_type=latlng&search=2&car=0&mileage=12.4&start=' + start_point_latlng["lat"] + "%2C" + start_point_latlng["lng"] + "%2C" + start_point_latlng["name"] + "&destination=" + end_point_latlng["lat"] + "%2C" + end_point_latlng["lng"] + "%2C" + end_point_latlng["name"]

route_point_info = request_data_from_url(url)

print(route_point_info)

route_latlngs = parse_naver_map_route_latlng(route_point_info)

print(route_latlngs)



