# JavaScript

## I. HTML Template

- New HTML File 에서 HTML Templates 에 들어가면 Templates를 수정할 수 있다.



## II. JavaScript

```javascript
alert('1')					// 알림창 생성

setInterval(function(){		// 1000밀리 초 마다 리프레시
    var now = new Date();	// Date 형 객체 생성
    document.write(now);
    
}, 1000);

var v6 = {					// 오브젝트 : JSON 방식
    name : 'k1',
    size : 1000,
    go:function(){},
    stop:function(){}
};

var h = document.getElementById('h');	// html 구문의 위치를 받아
h.innerHTML = result;					// 정보를 변경한다.
h.innerHTML = '<a href="">' + result + '</a>';	// html 을 추가

var h = document.getElementById('n1').value;	// value 값을 받아

if (isNaN(sum)) {			// sum 이 NaN 이면
    sum = 0;				// 값을 바꿔줌
}

```

- alert 은 주로 실행한 결과가 맞는지 확인하려고 사용한다 : 다 확인했으면 지워야한다.

```javascript
alert(a + parseInt(b));
alert(String(a));			// String 형식으로 변환
alert(Number(a));			// Number 형식으로 변환
```



- 브라우저에서 제공하는 콘솔에서도 변수나 함수를 확인할 수 있다.

```javascript
var p = prompt();			// 알림창에서 input 을 받을 수 있다.
console.log(p);				// log 창에서 확인할 수 있다.
```



- 이제 자바스크립트가 form submit 의 제어를 담당할 수 있다. : submit 을 쓰지 말자!

```javascript
<input onclick="login(this.form);" type="button" value="login">
```

- this.form 형태를 통해 자바스크립트에서 활용할 수 있다.

```javascript
	function login(f) {
		var id = f.id;
		var pwd = f.pwd;
		
		if (id.value == '' || id.value == null) {
			alert('Madatory field !');
			id.focus();								// id에 포커스
			
			return;
		}
		
		if (pwd.value == '' || pwd.value == null) {
			alert('Madatory field !');
			pwd.focus();
			
			return;
		}
		
		var c = confirm('Are you Login ?');
		
		if (c) {
			f.submit();	
		}
	};
```

- 랜덤하게 숫자를 얻고 싶을 때에는 Math.random() 을 사용한다.

```javascript
var cnum = Math.random();
```



## III. CodeMix

- Eclipse MarketPlace 에서 WebClipse 를 다운받을 수 있다.
- 그러면 자동완성에 도움을 받을 수 있다.