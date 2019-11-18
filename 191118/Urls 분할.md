# Urls 분할

## I. urls.py 나누기

- urls.py 에서 include 를 통해 articles 파일 안의 urls.py 로 연결해준다.

```python
# urls.py

urlpatterns = [
    path('admin/', admin.site.urls),
    path('articles/', include('articles.urls'))
]
```

- articles/urls.py 를 만들어서 평소처럼 하면 된다.

```python
from django.contrib import admin
from django.urls import path
from articles import views

urlpatterns = [
# 여기는 이미 앞 주소에 articles/ 가 붙어있다고 생각하면 된다.
    path('', views.index),
    path('<int:id>/', views.show),
    path('new/', views.new),
    path('create/', views.create),
    path('<int:id>/edit', views.edit),
    path('<int:id>/update', views.update),
    path('<int:id>/delete', views.delete),
]
```

## II. Bootstrap 가져오기

- getbootstrap.com > getStart
- CSS, JS 가지고 오기



## III. BASE 만들기

```html
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>BASE</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    {% block content %}
    {% endblock %}

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
```

- index.html

```html
{% extends 'base.html' %}
{% block content %}
<!-- 이 안에서 값들을 바꾸면 된당. 오아아아아ㅏ -->
{% endblock %}
```



- view.py 연결해주기

```python
from django.shortcuts import render, redirect

# Create your views here.

def index(request):
    return render(request, 'index.html')

def show(request):
    return render(request, 'show.html')

def new(request):
    return render(request, 'new.html')

def create(request):
    return redirect('/articles/')

def edit(request):
    return render(request, 'edit.html')

def update(request):
    return redirect('/articles/')

def delete(request):
    return redirect('/articles/')
```



## IV. Model 구축하기

- Model 틀 짜기 (models.py)

```python
from django.db import models

# Create your models here.

class Article(models.Model):
    title = models.CharField(max_length = 16)
    contents = models.TextField()
    creator = models.CharField(max_length = 8)
```



- python manage.py makemigrations
  - 0001_initial.py 가 생겼다!!
- python manage.py migrate
- 확인하고 싶을 땐?
  - python manage.py showmigrations



## V. 모델 가지고 놀기

- 이렇게 쉽게 가지고 놀 수 있다!!

```python
from django.shortcuts import render, redirect
from .models import Article

# Create your views here.

def index(request):
    articles = Article.objects.all()

    context = {
        'articles' : articles
    }

    return render(request, 'index.html', context)

def show(request, id):
    articles = Article.objects.get(id=id)

    context = {
        'articles' : articles
    }

    return render(request, 'show.html', context)

def new(request):
    return render(request, 'new.html')

def create(request):
    title = request.GET['title']
    contents = request.GET['contents']
    creator = request.GET['creator']

    article = Article()
    article.title = title
    article.contents = contents
    article.creator = creator

    article.save()

    return redirect(f'/articles/{article.id}/')

def edit(request, id):
    articles = Article.objects.get(id=id)

    context = {
        'articles' : articles
    }

    return render(request, 'edit.html', context)

def update(request, id):
    article = Article.objects.get(id=id)

    title = request.GET['title']
    contents = request.GET['contents']
    creator = request.GET['creator']

    article.title = title
    article.contents = contents
    article.creator = creator

    article.save()

    return redirect(f'/articles/{article.id}')

def delete(request, id):
    article = Article.objects.get(id=id)
    article.delete()

    return redirect('/articles/')
```



## VI. URL Namespace

- 각각의 url 에 별명을 지어준다.
- html 파일에서 사용하는 링크를 추가적으로 바꾸지 않고
- urls.py 에서만 수정하면 html 파일에서도 링크 수정이 반영되게끔!!
- ㅗㅜㅑ

```python
# urls.py

from django.contrib import admin
from django.urls import path
from articles import views

urlpatterns = [
    path('', views.index, name='index'),
    path('<int:id>/', views.show, name='show'),
    path('new/', views.new, name='new'),
    path('create/', views.create, name='create'),
    path('<int:id>/edit/', views.edit, name='edit'),
    path('<int:id>/update/', views.update, name='update'),
    path('<int:id>/delete/', views.delete, name='delete')
]
```

```html
<!-- index.html -->

<td>{{ article.id }}</td>
<!-- 이렇게 하면 파이썬이 알아서 맵핑해준다!! -->
<!-- 뒤에 인자도 넣어줄 수 있다. -->
<td>
    <a href="{% url 'show' article.id %}">{{ article.title }}</a>
</td>
<td>{{ article.creator }}</td>
<td></td>

<a href="{% url 'new' %}" class="btn btn-info">새글쓰기</a>
```

- 사용 위치
  - a href
  - form
  - redirect



- 그럼 만약 board 라는 app 에서도, articles 라는 app 에서도
- index 라는 이름이 있다면?
  - namespace 가 있어야 한다!!

```python
# urls.py
# 진짜 이거만 추가해 놓으면 articles:index 를 사용 가능하다!
app_name = 'articles'
```

```html
<!-- index.html -->
<!-- 'articles:show' 라고 접근이 가능하다!! -->

<td><a href="{% url 'articles:show' article.id %}">{{ article.title }}</a></td>
```



## VII. RESTful API

- 현재 우리의 Request 구조

| 역할   | Request-Method | End-Point              | Views(function) |
| ------ | -------------- | ---------------------- | --------------- |
| Create | GET            | /articles/new/         | new             |
| Create | GET            | /articles/create/      | create          |
| Read   | GET            | /articles/<id>/        | show            |
| Read   | GET            | /articles/             | index           |
| Update | GET            | /articles/<id>/edit/   | edit            |
| Update | GET            | /articles/<id>/update/ | update          |
| Delete | GET            | /articles/<id>/delete/ | delete          |

- 사실 GET, POST 두 개만 공식적으로 모든 브라우저에서 지원 가능하다
  - GET : 조회
  - POST : DB 에 반영해야 하는 데이터가 있을 때
- 바뀐 구조는?

| 역할   | Request-Method | End-Point              | Views(function) |
| ------ | -------------- | ---------------------- | --------------- |
| Create | GET            | /articles/new/         | new             |
| Create | POST           | /articles/             | create          |
| Read   | GET            | /articles/<id>/        | show            |
| Read   | GET            | /articles/             | index           |
| Update | GET            | /articles/<id>/edit/   | edit            |
| Update | POST           | /articles/<id>/        | update          |
| Delete | POST           | /articles/<id>/delete/ | delete          |

- 음... 다음으로 미뤄졌다...



## VIII. Model 시간

- 이제 Article Model에 시간을 추가하자!

```python
# models.py

from django.db import models

# Create your models here.

class Article(models.Model):
    title = models.CharField(max_length = 16)
    contents = models.TextField()
    creator = models.CharField(max_length = 8)
    created_at = models.DateTimeField(auto_now_add=True, null=True)
    updated_at = models.DateTimeField(auto_now=True, null=True)
```

- migration 생성
  - python manage.py makemigrations
  - python manage.py migrate
- 값을 따로 넣어주지 않아도 DJango 에서 알아서 시간을 넣어준다!!
- 오아아..



## X. 시간 예쁘게 만들기

- strftime을 쓰자!!

```python
# models.py

from django.db import models

# Create your models here.

class Article(models.Model):
    title = models.CharField(max_length = 16)
    contents = models.TextField()
    creator = models.CharField(max_length = 8)
    created_at = models.DateTimeField(auto_now_add=True, null=True)
    updated_at = models.DateTimeField(auto_now=True, null=True)

    def datetime_to_string(self):
        return self.created_at.strftime('%Y-%m-%d')
```

```html
<!-- index.html -->

{% for article in articles %}
    <tr>
        <td>{{ article.id }}</td>
        <td><a href="{% url 'articles:show' article.id %}">{{ article.title }}</a></td>
        <td>{{ article.creator }}</td>
<!-- 이렇게 models.py 에 있는 함수를 쓸 수 있다!! -->
        <td>{{ article.datetime_to_string }}</td>
        <td>{{ article.updated_at }}</td>
    </tr>
{% endfor %}
```



## XI. admin

- localhost:8000/admin
- 관리자 계정 생성
  - python manage.py createsuperuser

- 이제 관리자 권한으로 들어갈 수 있다!

- articles 를 admin 에 등록하면 관리할 수 있께 된다!

```python
# articles/admin.py

from django.contrib import admin
from .models import Article

# Register your models here.

admin.site.register(Article)
```

- 이제 Article 관리를 할 수 있는데
- 혹시 무슨 글인지 알고 싶다면?

```python
from django.db import models

# Create your models here.

class Article(models.Model):
    title = models.CharField(max_length = 16)
    contents = models.TextField()
    creator = models.CharField(max_length = 8)
    created_at = models.DateTimeField(auto_now_add=True, null=True)
    updated_at = models.DateTimeField(auto_now=True, null=True)

    def __str__(self):
        return f'[{self.datetime_to_string()}] - {self.title} created by {self.creator}'

    # 이렇게 toString 처럼 만들 수 있는 함수가 있다
    # 이러면 admin 에서 볼 때 예쁘게 볼 수 있음
    
    def datetime_to_string(self):
        return self.created_at.strftime('%Y-%m-%d')
```

