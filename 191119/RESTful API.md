# RESTful API

| 역할   | Request-Method | End-Point              | Views(Functions) |
| ------ | -------------- | ---------------------- | ---------------- |
| Create | GET            | /articles/new/         | new              |
| Create | POST           | /articles/new/         | create > new     |
| Read   | GET            | /articles/<id>/        | show             |
| Read   | GET            | /articles/             | index            |
| Update | GET            | /articles/<id>/edit/   | edit             |
| Update | POST           | /articles/<id>/edit/   | update > edit    |
| Delete | POST           | /articles/<id>/delete/ | delete           |



## I. CSRF 토큰

- POST 로 요청을 보내려면 CSRF 토큰도 제출되어야만 한다.

```html
{% extends 'base.html' %}
{% block content %}
<div class="container">
    <form action="{% url 'articles:new' %}" method="POST">
        <div class="form-group">
<!-- 여기에 토큰과 name 이 등록되었다. -->
            <input type="text" class="form-control" name="csrfmiddlewaretoken" value="{{ csrf_token }}" readonly="true">
        </div>
        <div class="form-group">
            <label for="title">Title</label>
            <input type="text" class="form-control" id="title" name="title" placeholder="제목을 입력하세용">
        </div>
        <div class="form-group">
            <label for="contents">Contents</label>
            <textarea rows=5 class="form-control" id="contents" name="contents" placeholder="내용"></textarea>
        </div>
        <div class="form-group">
            <label for="creator">Creator</label>
            <input type="text" class="form-control" id="creator" name="creator" placeholder="글쓴이">
        </div>

        <button type="submit" class="btn btn-primary">작성하기</button>
    </form>
</div>
{% endblock %}
```

- 이제 POST 방식으로 보내도 요청이 된다!!



## II. 변화된 부분

- urls.py

```python
from django.contrib import admin
from django.urls import path
from articles import views

app_name = 'articles'

urlpatterns = [
    path('', views.index, name='index'),
    path('<int:id>/', views.show, name='show'),
    
    path('new/', views.new, name='new'),
    path('<int:id>/edit/', views.edit, name='edit'),
    path('<int:id>/delete/', views.delete, name='delete')
]
```

- views.py

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
    if request.method == 'POST':
        title = request.POST['title']
        contents = request.POST['contents']
        creator = request.POST['creator']

        article = Article()
        article.title = title
        article.contents = contents
        article.creator = creator

        article.save()

        return redirect('articles:show', article.id)
    
    else:
        return render(request, 'new.html')

def edit(request, id):
    if request.method == 'POST':
        article = Article.objects.get(id=id)

        title = request.POST['title']
        contents = request.POST['contents']
        creator = request.POST['creator']

        article.title = title
        article.contents = contents
        article.creator = creator

        article.save()

        return redirect('articles:show', article.id)

    else:
        articles = Article.objects.get(id=id)

        context = {
            'articles' : articles
        }

        return render(request, 'edit.html', context)

def delete(request, id):
    article = Article.objects.get(id=id)
    article.delete()

    return redirect('articles:index')
```

- edit.html

```html
{% extends 'base.html' %}
{% block content %}
<div class="container">
    <form action="{% url 'articles:edit' articles.id %}" method="POST">
        <div class="form-group">
            <input type="text" class="form-control" name="csrfmiddlewaretoken" value="{{ csrf_token }}" readonly="true">
        </div>
        <div class="form-group">
            <label for="title">Title</label>
            <input type="text" class="form-control" id="title" name="title" value="{{ articles.title }}" placeholder="제목을 입력하세용">
        </div>
        <div class="form-group">
            <label for="contents">Contents</label>
            <textarea rows=5 class="form-control" id="contents" name="contents" placeholder="내용">{{  articles.contents }}</textarea>
        </div>
        <div class="form-group">
            <label for="creator">Creator</label>
            <input type="text" class="form-control" id="creator" name="creator" value="{{ articles.creator }}" placeholder="글쓴이" readonly="true">
        </div>

        <button type="submit" class="btn btn-primary">수정하기</button>
    </form>
</div>
{% endblock %}
```

- Delete 할 때 생긴 문제점!
  - a 태그가 GET 방식으로밖에 못 보내기 때문에,
  - 바꾸기 싫은 현상이 발생했다!!