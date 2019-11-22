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



## IV. attribute

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



## VII. Form Control

```html
<form>
    <div class="mb-5">
        <div>
            <input type="text" class="form-control" id='articleForm'>
            <input type="button" class="btn btn-primary" id="submitArticle" value="글쓰기">
        </div>
        <div>
            <input type="text" class="form-control" id='commentForm'>
            <input type="button" class="btn btn-primary" id="submitComment" value="댓글쓰기">
        </div>
    </div>
</form>

<script>
    var btn = document.getElementById('submitComment');

    btn.addEventListener('click', function(){
        var commentForm = document.getElementById('commentForm');
        console.log(commentForm.value);
    });
</script>
```

- BackTip

```html
var appendingTag = `<li class="list-group-item"> 
    네번째 댓글 입니다.
    <button class="btn btn-info float-right">수정</button>
</li>`;

<!--  `` 이것을 사용하면 멀티라인으로 구성이 가능하다. -->

position.appendChild(appendingTag);

<!--  이거 태그 형식이 아닌 스트링 형식이기 때문에 받을 수 없다!! -->
<!--  이것을 쓰기 위해서는 태그 형식으로 만들어 줘야하ㅐ -->

position.innerHTML += appendingTag;

<!--  이거 됨 -->
```

- 새로운 태그 생성

```html
<script>
    var btn = document.getElementById('submitComment');

    btn.addEventListener('click', function(){
        var commentForm = document.getElementById('commentForm');

        if (commentForm.value == '') {
            alert('안돼안돼');
            return;
        }

        var position = document.querySelector('.list-group');

        var appendingTag = `<li class="list-group-item">` +
            commentForm.value +
            `<button class="btn btn-info float-right">수정</button>
    </li>`;

        var newTagLi = document.createElement('li');
        newTagLi.classList.add('list-group-item');
        newTagLi.innerText = commentForm.value;

        var newTagSpan = document.createElement('span');
        newTagSpan.classList.add('float-right');
        newTagSpan.innerHTML = `<button class="btn btn-info float-right">수정</button>`;

        newTagLi.appendChild(newTagSpan);
        position.appendChild(newTagLi);

        commentForm.value = '';
    });
</script>
```

## VIII. JQuery

- 로드 되었는지를 확인

```html
<script>
    $(document).ready(function(){

    });

    $(function(){

    });
    // 둘 다 똑같은 의미다!!
</script>
```

- 자동으로 여러개가 나와도 for 문을 돌려준다.

```html
<script>
    $(document).ready(function(){
        $('.delete-comment').on('click', function(){
            alert('삭제에');
        });
    });
</script>
```

- 만약 버튼을 눌렀을 때 해당 글을 지우고 싶다면?
  - 일단 attr 로 attribute을 가지고 와야 한다.

```html
<script>
    $(document).ready(function(){
        $('.delete-comment').on('click', function(){
            console.dir($(this));

            var commentContent = $(this).attr('value');
            console.log(commentContent);
        });
        
        // 그러면 id 를 가지고 와서 없애줄 것인가?
        // 아니다!!
        // 부모 정보 또한 가지고 올 수 있다!!
        
        $(this).parent().hide();
        
        // 이렇게 하거나
        
        $(this).parents('.list-group-item').hide();
        
        // 저렇게 하거나
    });
</script>
```

- 새로운 글 쓸때는?

```html
<script>
    $(document).ready(function(){
        $('#submitComment').on('click', function(){
            var position = $('ul.list-group');
            var input = $('#commentForm').val();

            var element = ` <li class="list-group-item">
${ input }
<button class="btn btn-warning float-right delete-comment">삭제</button>
<button class="btn btn-info float-right">수정</button>
    </li>`;
// 이거바!! ${input} 으로 값을 넣어줬어!!
            position.prepend(element);
        });

        $('.delete-comment').on('click', function(){
            $(this).parents('.list-group-item').hide();
        });
    });
</script>
```

- 그런데.. 새로 등록된 글은 이벤트가 등록이 되어있지 않다??
- 그러면?

```javascript
$(document).on('click', '.delete-comment', function(){
	$(this).parents('.list-group-item').hide();
});

// 이렇게 추가된 부분도 다시 찾을 수 있도록
// document 부터 추적하도록 하자!!
```



