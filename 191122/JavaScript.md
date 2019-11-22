# JavaScript

## I. element 찾기

- getElementById
- getElementsByClassName
- getElementsByTagName
- querySelector
- querySelectorAll



- querySelector 는 getElementsByClassName 과 무엇이 다를까?
  - querySelector : NodeList 반환
    - foreach 가 있다
  - getElemtnsByClassName : HTMLCollection 반환
    - foreach 가 없다?
    - JavaScript 의 단점이 여기다...



## II .CSS Selector

- . : class
- ''#'' : id
- .p-list span : p-list class 안의 span 태그
- .p-list > span : p-list class 안의 직계 span 태그



## III. Event

- onclick

```html
<script>
    var joke = document.getElementById('joke');
    joke.onclick = function() {
        alert('하하');
    };
</script>
```

- 좀 더 직관적인 방법?

```html
<script>
	joke.addEventListener('click', function(){
       alert('gkgk');
    });
</script>
<!--    joke : Element
		addEventListener : listener
		'click' : event type
		function() : event handler -->
```

- 확인은?
  - 개발자 옵션 > Event Listeners 에서 확인 가능



## VI. attribute

- 속성값 부여

```html
<style>
    .bg-red {
        background-color : red;
    }
</style>

<script>
	element.addEventListener('click', function(){
        element.setAttribute('class', 'p-tag');
    })
    
    element.addEventListener('click', function(){
        element.setAttribute('style', 'display : none;');
    })
    
    element.addEventListener('click', function(){
        element.classList.add('bg-red');
    })
    
    element.addEventListener('click', function(){
        element.classList.remove('bg-red');
    })
    
    element.addEventListener('click', function(){
        element.classList.toggle('bg-red');
        
        // 이 toggle 기능을 사용하면
        // 해당 클래스를 넣었다 뺐다 할 수 있다.
    })

// classList 같은 경우에는 새 클래스가 추가되는 것이고
// setAttribute('class', 'p-tag') 같은 경우에는
// 모든 클래스가 삭제되고 p-tag 만 남게 된다.
</script>
```



## V. Value

- 태그 내용물 가져오기

```html
<script>
	console.dir('p');
    // p 태그가 가지고 있는 속성값까지 가지고 올 수 있다.
    
    element.addEventListener('click', function(){
        alert(element.innerText);
        alert(element.innerHTML);
    })
</script>
```



## VI. Bootstrap

- 해당 CSS 클래스를 javascript 를 가지고 사용할 수 있다.

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>SCRIPT TEST</title>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <!-- Bootstrap 을 가지고 오면 -->
    </head>
    <body>
        <div>요소1</div>
        <div>요소2</div>
        <div>요소3</div>
        <div>요소4</div>
        <div>요소5</div>
    </body>

    <script>
        var divList = document.querySelectorAll('div');

        divList.forEach(function(element) {
            element.addEventListener('click', function(){
                element.classList.toggle('alert');
                element.classList.toggle('alert-primary');
            });
        });
        
        // 이렇게 Bootstrap 클래스를 이용해서 CSS 를 꾸밀 수 있다.
    </script>
</html>
```

