# dJAVASCRIPT

## I. JSON 방식

- JSON 방식 = OBJECT 형식

```javascript
var person = {
   	id : 'id01',
    name : 'Aha'
};

// person[i].id, person[i].name 으로 접근 가능하다.
```

- 함수 선언 때, 이름 중복을 조심해라! : 빌트 인 함수도 조심해야 한다.
- ex) clear

```javascript
function a() {
    
};

function a() {				// 밑에 것이 실행된다!
    
};

a();						// 함수 실행은 이렇게!
```

- 함수 선언은 두 가지 방법이 있다.

```javascript
function a() {
    
};

var a = function() {
    
};
```

- 함수는 리턴 타입도 없고, 아규먼트도 타입이 없다.

```javascript
function a(c,d) {			// 하지만 아규먼트에 무엇이 들어갈 지 모르							 // 므로 주석을 잘 달자!
	alert(c + d);  
};
```

- 아규먼트로는 함수도 들어갈 수 있다.

```javascript
function c(i , j) {
	var result = i(2) + j();			// 함수의 아규먼트는 함수 안										 // 에서 넣어주면 된다.
    return result;
};

c(a, b);					// 호출할 때에는 a 라는 함수 이름만 삽입
```

- 삽입할 수 있는 값에는 unknown 함수를 넣을 수도 있다.

```javascript
var data = c(a, function(){				// 이 때는 함수 끝에 ; 을 붙										   // 이지 않는다.
    return 500;
});
```

- 함수는 함수를 리턴할 수도 있다.

```javascript
var f1 = function() {
	return function(i) {
  		return 100 * i;
    };
};

var f2 = f1();
var result = f2(2);					// 이렇게 함수 안 함수의 아규먼트									// 를 넣어준다.

alert(result);
```

- 자바스크립트의 클래스 형식은 function 이다 : 잘 사용되지는 않는다.

```javascript
function Person(name, age) {
    this.name = name;
    this.age = age;
    this.print = function(){	// 객체가 많이 생성될 때											// 에는 function 은 최대한 자제한다.
        return this.name + ' ' + this.age;
    };
};

// 함수가 많이 올라가서 속도가 느려지는 현상을 극복하기 위함
// 프로토타입을 만들면 이 함수는 한 번만 올라감
Person.prototype.print = function() {
	return this.name + ' ' + this.age;
};

var p1 = new Person('James', 30);		// 클래스는 마찬가지												// 로 new 키워드를 붙여준다.
var p2 = new Person('Tom', 20);
```

- 만약 객체에 해당 함수가 없다면 함수를 추가해 줄 수 있다. : 속도 문제는 어떻게 되는지는 잘 모르겠음..

```javascript
		for (var i in person) {
			person[i].print = function() {
				return '<h2>' + this.id + ' ' + this.name + 					'</h2>';
			};
		}
```

- 같은 이름의 함수를 따로 쓰고 싶을 때에는 객체를 생성해라!!

```javascript
var Sk = {
	a : function() {
        alert('sk');
    }  
};

var Hb = {
	a : function() {
        alert('hb');
    }
};

Sk.a();						// 서로 다른 결과를 낼 수 있음
Hb.a();
```



- getElementById 와 getElementsByName 의 차이를 알아둬야 한다.

```javascript
var drink = document.getElementsByName('drink');
// drink 의 name 이 여러개이기 때문에 ByName 으로 사용 가능
var pay = document.getElementById('pay');
// var pay = document.getElementsByName('pay'); -> 오류!!
// pay 의 name 이 한개이기 때문에 ByName 으로 사용 불가능
// 한개만 있는 것은 무조건 ById 로 받아와야만 한다.
...

<div>
    포카리 <input type="checkbox" name="drink" value="fokari">
    게토레이 <input type="checkbox" name="drink" value="getorai">
    2프로 <input type="checkbox" name="drink" value="ipro">
</div>

지불방법
<select id ="pay" name="pay">
        <option value="card">카드</option>
		<option value="cash">현금</option>
</select>
```



## II. 찾기

- Search -> Search 에서 찾고싶은 문자열을 치고 엔터를 치면 해당 문자열이 있는 곳을 표시해준다.



### III. Error

- 자바 스크립트는 Exception 과 Error 를 구분하지 않고 무조건 Error 라 판단

```javascript
try {
    c = new Daate();
} catch(error) {
 	alert('Network error...');
}
```

