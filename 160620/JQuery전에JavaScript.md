# JQuery

## I. 전에 JavaScript

- setTimeout

```javascript
setTimeout(function(){				// 3초 뒤에 해당 함수 실행
    alert('HI');
}, 3000);
```

- window.onload

```javascript
window.onload = function(){			// HTML 로드가 끝나면 바로 실행
    alert('Load Complete');
};
```

- setInterval 과 setTimeout 의 조화 : function 을 마음대로 반복하고 정지할 수 있다!!

```javascript
var h1 = document.getElementById('h1');
var h2 = document.getElementById('h2');
var h3 = document.getElementById('h3');

alert(h1 + h2 + h3);

var s1 = setInterval(function(){}, 200);	// 0.2 초마다
var s2 = setInterval(function(){}, 500);
var s3 = setInterval(function(){}, 400);

setTimeout(function(){
    clearInterval(s1);						// s1 정지
    clearInterval(s2);
    clearInterval(s3);
}, 5000);
```

