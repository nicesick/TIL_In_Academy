# CHART

## I. 시작

- www.highcharts.com

- 필요한 템플릿에서 -> edit in jsfiddle -> 소스 복사 (html , js, css)



## II. 데이터 방식

- JSON 방식

```javascript
[{
    name: 'Tokyo',
    data: [7.0, 6.9, 9.5, 14.5, 18.4, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
}, {
    name: 'London',
    data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
}]
```



- xml 방식

  1. web 에서 temp.xml 생성

  2. AJAX url 부분을 temp.xml 로 변경

  3. 받은 xml 을 해석할 수 있는 parsing 을 생성해서 배열 형태로 재생성

1. 

```xml
<?xml version="1.0" encoding="UTF-8"?>
<temps>								// 사용자 정의 태그
	<temp>
		<name>Seoul</name>
		<data>10</data>
		<data>20</data>
		<data>30</data>
		<data>40</data>
		<data>50</data>
		<data>60</data>
		<data>70</data>
		<data>80</data>
		<data>90</data>
		<data>100</data>
		<data>110</data>
		<data>120</data>
	</temp>
	<temp>
		<name>Busan</name>
		<data>5</data>
		<data>10</data>
		<data>20</data>
		<data>25</data>
		<data>50</data>
		<data>70</data>
		<data>90</data>
		<data>120</data>
		<data>200</data>
		<data>300</data>
		<data>500</data>
		<data>500</data>
	</temp>
</temps>
```



2. 

```javascript
function getData() {
    $.ajax({
        url : 'temp.xml',
        success : function(data){
            makeChart(data);
        }
    });
};
```



3. 

```javascript
function Temp(name, data) {
    this.name = name;
    this.data = data;
};

function parsing(data) {
    var ts = $(data).find('temp');					// xml 분석을 find 함수를 써서 사용한다.

    var datas = [];

    $(ts).each(function(idx, item){
        var name = $(this).find('name').text();
        var data = $(this).find('data');

        var values = [];

        $(data).each(function(idx, item){
            values.push(parseInt($(item).text()));
        });

        var obj = new Temp(name, values);			// 클래스를 사용해도 분석 가능하다!
        datas.push(obj);
    });

    makeChart(datas);
};
```



## III. OpenAPI

- www.data.go.kr
- 이 데이터를 사용하기 위해서는 키가 필요함
- https://openapi.gg.go.kr/ChildPlayFacility?Type=json&pSize=10
  - 필요한 정보를 ? 뒤에 붙여서 선택할 수 있다.
  - ?Type=json&pSize=10 : &를 이용해 연결할 수 있다.