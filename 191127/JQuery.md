# JQuery

## I. Select 기반 find

```html
<!-- index.html -->

<script>
...

$(this).find('input.commentInput').val();
// selector 기반으로 내부 내용을 찾는다.

...
</script>
```



## II. 댓글 컨텐츠 가져오기

- 내 방법

```html
<span class="item-contents">{{ comment.contents }}</span>

<!-- 나는 $('#' + comment_id + ' .item-contents').text() 로 접근 했다. -->
```



- 나와는 다른 방법

```html
<button class="btn btn-warning edit-comment" data-article_id="{{ article.id }}" data-comment_id="{{ comment.id }}" data-contents={{ comment.contents }}><i class="fas fa-edit"></i></button>

<!-- 아예 정보를 data 에 다 박아넣었다!! -->
<!-- $(this).data('contents') 로 가져올 수 있다 -->
```



## III. 댓글 수정 AJAX 요청

- 내 방법

```html
<script>
    $(document).on('click', '.submit-comment', function(event){
        event.preventDefault();
        var method = $(this).data('method');
        
        if (method == 'POST') {
            $.ajax({
                ...
            });
        }
        
        else {
            $.ajax({
            	...       
            });
        }
</script>
```

- 나와는 다른 방법

```html
<script>
    $('.comment-submit').on('submit', function(){
    	console.log($(this).serialize());
        // serialize 는 form 안에 있는 모든 input 태그 정보를 받아올 수 있다.
        // 단, this 가 form 제출 관련된 거여야 한다.

        var data = $(this).serialize();

        $.ajax({
            url : ...,
            method : ...,
            data : data,
            
            // serialize 된 값을 바로 데이터로 넘겨 버리자!
            ...
        });    
    });
</script>
```



## IV. account 기능 생성

- superuser 생성
  - python manage.py createsuperuser

- account application 생성
  - python manage.py startapp accounts

- setting.py 에 accounts 등록

- urls.py include 및 pattern 생성

```python
from django.urls import path
from . import views as accounts_views

app_name = 'accounts'

urlpatterns = [
    path('/login', accounts_views.login, name='login'),
    path('/logout', accounts_views.logout, name='logout'),
    path('/signup', accounts_views.signup, name='signup')
]
```

- account 를 위한 templates 만들기

```html
<!-- login.html -->
<!doctype html>
<html lang="ko">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Jekyll v3.8.6">
        <meta name="theme-color" content="#563d7c">

        <title>Signin Template · Bootstrap</title>

        <link rel="canonical" href="https://getbootstrap.com/docs/4.4/examples/sign-in/">
        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.0/css/bootstrap.min.css"
              integrity="sha384-SI27wrMjH3ZZ89r4o+fGIJtnzkAnFs3E4qz9DIYioCQ5l9Rd/7UAa8DHcaL8jkWt" crossorigin="anonymous">

        <!-- Custom styles for this template -->
        <link href="https://getbootstrap.com/docs/4.4/examples/sign-in/signin.css"
              integrity="sha384-SI27wrMjH3ZZ89r4o+fGIJtnzkAnFs3E4qz9DIYioCQ5l9Rd/7UAa8DHcaL8jkWt" crossorigin="anonymous">
    </head>

    <body class="text-center m-5">
        <form class="form-signin" method="POST">
            <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>

            <label for="inputEmail" class="sr-only">Email address</label>
            <input type="email" id="inputEmail" name="username" class="form-control" placeholder="Email address" required autofocus>

            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" id="inputPassword" name="password" class="form-control" placeholder="Password" required>
            <div class="checkbox mb-3">
                <label>
                    <input type="checkbox" value="remember-me"> Remember me
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
            <p class="mt-5 mb-3 text-muted">&copy; 2017-2019</p>
        </form>
    </body>

</html>
```



## V. cookies, session

- Http 의 무상태성 때문에 생김

- 때문에 정보 저장 용도로 cookies, session 이 생김



- Cookies
  - 정보 저장 위치 : 내 브라우저
  - 단, 민감한 유저 데이터 같은 경우에는 위험함 (보안에 취약)



- Session
  - 정보 저장 위치 : 서버
  - 유저는 단지 서버에 저장되어 있는 위치만 조회하면 된다!!
  - 주로 cookies 로 sessionid 라는 식으로 저장되어 있다.



## VI. Auth 기능 사용

- user 기능 import
  - from django.contrib.auth.models import User
- 가입정보 유효 판단 import
  - from django.contrib.auth.forms import UserCreationForm, AuthenticationForm

- 로그인 기능 import
  - from django.contrib.auth import login as auth_login
  - from django.contrib.auth import logout as auth_logout
- signup.html 구현

```html
<!-- signup.html -->
<!-- preparation -->

<!-- username : 아이디 -->
<!-- password1 : 비밀번호 -->
<!-- password2 : 비밀번호 확인 -->

<!doctype html>
<html lang="ko">

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
        <meta name="generator" content="Jekyll v3.8.6">
        <meta name="theme-color" content="#563d7c">

        <title>Signup Template · Bootstrap</title>

        <link rel="canonical" href="https://getbootstrap.com/docs/4.4/examples/sign-in/">

        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.0/css/bootstrap.min.css"
              integrity="sha384-SI27wrMjH3ZZ89r4o+fGIJtnzkAnFs3E4qz9DIYioCQ5l9Rd/7UAa8DHcaL8jkWt" crossorigin="anonymous">

        <!-- Custom styles for this template -->
        <link href="https://getbootstrap.com/docs/4.4/examples/sign-in/signin.css"
              integrity="sha384-SI27wrMjH3ZZ89r4o+fGIJtnzkAnFs3E4qz9DIYioCQ5l9Rd/7UAa8DHcaL8jkWt" crossorigin="anonymous">
    </head>

    <body class="text-center m-5">
        <form action="{% url 'accounts:signup' %}" class="form-signin" method="POST">
            <h1 class="h3 mb-3 font-weight-normal">Please sign up</h1>

            <input type="hidden" name="csrfmiddlewaretoken" value="{{ csrf_token }}">

            <label for="inputEmail" class="sr-only">Email address</label>
            <input type="email" id="inputEmail" name="username" class="form-control" placeholder="Email address" required autofocus>

            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" id="inputPassword" name="password1" class="form-control" placeholder="Password" required>
            <input type="password" id="inputPassword" name="password2" class="form-control" placeholder="Password Confirmation" required>
            <div class="checkbox mb-3">
                <label>
                    <input type="checkbox" value="remember-me"> Remember me
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign up</button>
            <p class="mt-5 mb-3 text-muted">&copy; 2017-2019</p>
        </form>
    </body>

</html>
```

- views.py 기능 구현

```python
from django.shortcuts import render, redirect
from django.contrib.auth.models import User
from django.contrib.auth.forms import UserCreationForm, AuthenticationForm

from django.contrib.auth import login as auth_login
from django.contrib.auth import logout as auth_logout

# Create your views here.

def login(request):
    if request.method == 'POST':
        form = AuthenticationForm(request, request.POST)

        if form.is_valid():
            user = form.get_user()
            auth_login(request, user)

            return redirect('articles')
        else:
            return render(request, 'login.html')
    else:
        return render(request, 'login.html')

def logout(request):
    auth_logout(request)

    return redirect('accounts:login')

def signup(request):
    if request.method == 'POST':
        form = UserCreationForm(request.POST)

        if form.is_valid():
            user = form.save()
            auth_login(request, user)

            return redirect('articles')
        else:
            return render(request, 'signup.html')
    else:
        return render(request, 'signup.html')
```



## VII. 유저 정보 받기

- 로그인된 유저 정보는 user 에 저장된다.

```html
{% if user.is_authenticated %}
	{{ user.username }}
	<a class='...' href='{% url 'accounts:logout' %}'> sign out</a>
{% else %}
	<a class='...' href='{% url 'accounts:login' %}'> sign in</a>
{% endif %}
```



## VIII. 로그인, 회원가입 페이지 우회

- 만약 회원가입 페이지에 들어가려고 했는데, 이미 로그인이 되어있는 상태라면?

```python
# views.py

def login(request):
    if request.method == 'POST':
        form = AuthenticationForm(request, request.POST)

        if form.is_valid():
            user = form.get_user()
            auth_login(request, user)

            return redirect('articles')
        else:
            return render(request, 'login.html')
    else:
        if request.user.is_authenticated:
            return redirect('articles')
        else:
            return render(request, 'login.html')

def signup(request):
    if request.method == 'POST':
        form = UserCreationForm(request.POST)

        if form.is_valid():
            user = form.save()
            auth_login(request, user)

            return redirect('articles')
        else:
            return render(request, 'signup.html')
    else:
        # 이렇게 signup 페이지에 들어가려고 했는데,
        # user 정보를 읽어본다.
        
        if request.user.is_authenticated:
            return redirect('articles')
        else:
            return render(request, 'signup.html')
```

