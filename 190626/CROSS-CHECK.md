# CROSS-CHECK

## I. 이유

- 만약 한 컴퓨터에서 각자 다른 서버로 접속이 가능하다면, 한 서버에서 받은 내용을 다른 서버로 빼돌릴 가능성이 생긴다.
- 따라서 보안상의 이유로 한 서버에만 접속 가능하다.
- 이를 어기면 CROSS-CHECK PROBLEM 이 생기는 것!



- 하지만 두 서버에 동시에 접근해야 할 때가 생기는데, 그 때 각 서버가 신뢰할 수 있다는 코드는 넣어주면 둘 다 접근 가능하다.



## HTML5 에서 추가된 유용한 기능들!!



## II. 브라우저 Storage

- Session Storage : 브라우저 껐다 키면 사라지는 스토리지
- Local Storage : 브라우저 껐다 켜도 지속되는 스토리지



```javascript
sessionStorage.m = 'sid01';			// key, value 형식으로 저장된다.
localStorage.m = 'lid01';
```



## III. 웹소켓

- 웹 서비스에서도 TCP/IP 통신처럼 할 수 있도록 도와주는 기능
- 보통은 클라이언트가 요청하는 서버가 답장하는 식이었다.
- 하지만 서버에서도 클라이언트에게 메시지를 보낼 수 있따 :)



## IV. 서버 이벤트

- 웹소켓이랑 비슷하지만, 서버에서 무슨 이벤트가 일어나면 클라이언트에게 이벤트를 준다.

- Server Event 를 통해 서버가 클라이언트에게 이벤트를 줄 수 있다.
- 이것은 jsp 파일을 이용한다.
- 그리고 서버 프로그램이지만 web 디렉토리 안에 생성을 해야 한다.
- web -> jsp file 생성

```jsp
<%@ page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% 
	response.setContentType("text/event-stream;charset=utf-8");
	Date time = new Date();
%>

data:<%=time %>

// 근데 잘 안됨ㅎㅎ
```

```javascript
$(document).ready(function(){
    if (typeof(EventSource) !== 'undefined') {
        var app = new EventSource('test.jsp');

        app.onmessage = function(ev){
            $('h3').text(ev.data);
        };
    }

    else {
        alert('Server Event Not Available');
    };
});
```

## V. Drag and Drop

```html
<h1>Drag and Drop</h1>
<h3>ITEM</h3>
<div id="item" ondrop="itemdrop(event)" ondragover="itemover(event)" ondragleave="itemleave(event)">
    <img id="m1" src="img/m1.jpg" draggable="true" ondragstart="imgdrag(event)">
    <img id="m2" src="img/m2.jpg" draggable="true" ondragstart="imgdrag(event)">
    <img id="m3" src="img/m3.jpg" draggable="true" ondragstart="imgdrag(event)">
    <img id="m4" src="img/m4.jpg" draggable="true" ondragstart="imgdrag(event)">
    <img id="m5" src="img/m5.jpg" draggable="true" ondragstart="imgdrag(event)">
</div>
<h3>CART</h3>
<div id="cart" ondrop="cartdrop(event)" ondragover="cartover(event)" ondragleave="cartleave(event)"></div>

<h3></h3>
```

```javascript
function itemdrop(e) {
    e.preventDefault();
    var src = e.dataTransfer.getData('imgId');
    $('#item').append($('#' + src));
};

function itemover(e) {
    e.preventDefault();
};

function cartdrop(e) {
    e.preventDefault();
    var src = e.dataTransfer.getData('imgId');
    $('#cart').append($('#' + src));
};

function cartover(e) {
    e.preventDefault();
};

function imgdrag(e) {
    e.dataTransfer.effectAllowed = 'move';
    e.dataTransfer.setData('imgId',e.target.id);
};

function itemleave(e) {
};

function cartleave(e) {
};
```



## VI. MAP

- googleMap

```html
<script src="https://maps.google.com/maps/api/js?key=MY_KEY"></script>
```

- 지도를 쓰기 위해서는 항상 id 와 width 와 height, div 가 있어야 한다.

```javascript
var center = new google.maps.LatLng(36.798802, 127.160165);
var map = new google.maps.Map(
    document.querySelector('#map') , {
        mapTypeId : google.maps.MapTypeId.ROADMAP,
        zoom : 17,
        center : center
    });
```



- HTML5 Geolocation

```javascript
var watch = window.navigator.geolocation.watchPosition(
    success,
    error,
    {
        enableHighAccuracy : true,
        timeout : 10000,
        maximumAge : 0
    }
);

function success(position) {
    var center = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
    map.setCenter(center);
};

function error() {

};
```



- 이와 같이 HTML5에서 자신의 위치를 받아서, google Maps 에서 해당 위치를 센터로 만드는 방식을 사용한다.