# JavaScript 계속 + AJAX

## I. JavaScript 종류

- 바닐라
- JQuery
- React

- vue



## II. AJAX

- 비동기 자바스크립트 & XML
  - 비동기?
    - 실행을 다른 친구한테 맡기고,
    - 수행이 된 후 완료보고(Callback) 만 받는 거임
  - JQuery 에서 AJAX 기본 구조

```html
<script>
    $(function(){
        $.ajax({
            url : ,
            method : ,
            data : {} ,
            
            success : function(data){
            	
        	},
            
            error : function(data){
                
            }
        });
    });
</script>
```



## III. 기본 submit 이벤트 제거

```html
{% extends 'base.html' %}
{% block contents %}
<form id="boardForm">
    <input type="text" class="form-control">
    <input type="submit" class="btn btn-info">
</form>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script>
    $('#boardForm').on('submit', function(event){
        event.preventDefault();
        
        // 이렇게 하면 평소 하던 이벤트가 발생하지 않는다.
    });
</script>
{% endblock %}
```



## IV. 데이터 컨트롤

- 데이터를 넣을 때에는 어떻게 하지?

```html
<input type="button" class="btn btn-info" data-value="{{ comment.id }}">

...

<script>
	$('.deleteBoard').on('click', function(){
        id = $(this).data('value');
        // data-value 라고 하면 해당 값을 저장했다가 가져올 수 있다.
    });
</script>
```



- 그러면 데이터를 지울 때에는?

```html
<script>
    $('.deleteBoard').on('click', function(){
        id = $(this).removeData('value');
        // 이렇게 데이터 값을 지울 수 있다.
    });
</script>
```



## V. JSON 보내기

- json 형태로 데이터 response 하기
  - 주의해야 할 점
    - Object 는 json 에 넣을 수 없다.
    - 기본적인 (int, str, arr, dict) 같은 포맷을 넣을 수 있음

```python
from ... import render, redirect
from django.http.response import HttpResponse
import json

...

def delete_comment(request):
    ...
    # context 는 json 형태로 구성되어 있다.
    
    return HttpResponse(json.dumps(context), content_type='application/json')
```



- 현재까지 코드

```python
# views.py

from django.shortcuts import render, redirect
from django.http import HttpResponse

from .models import Board

import json

# Create your views here.

def index(request):
    boards = Board.objects.all().order_by('created_at').reverse()

    context = {
        'boards' : boards
    }

    return render(request, 'index.html', context)

def comment_ajax(request):
    if request.method == 'POST' and request.is_ajax():
        contents = request.POST['contents']

        board = Board()
        board.contents = contents

        board.save()

        context = {
            'board' : board
        }

        return render(request, 'list-item.html', context)

    def delete_comment_ajax(request):
        if request.method == 'POST' and request.is_ajax():
            id = request.POST['id']

            board = Board.objects.get(id=id)
            board.delete()

            context = {
                'id' : id
            }

            return HttpResponse(json.dumps(context), content_type='application/json')
```

```html
<!-- index.html -->

{% extends 'base.html' %}
{% block contents %}

<form id="boardForm">
    <div class="container mt-3 text-center">
        <div class="col-12">
            <div class="row">
                <div class="col-9">
                    <input type="text" class="form-control" id='boardInput'>
                </div>
                <div class="col-3">
                    <input type="submit" class="btn btn-info">
                </div>
            </div>
        </div>
    </div>
</form>

<div class="container mt-3">
    <div class="row">
        <ul class="list-group col-12">
            {% for board in boards %}
            <li class="list-group-item" id="{{ board.id }}">
                <div class="col-12">
                    <p>
                        {{ board.contents }}

                    <div class="text-right">
                        <input type="button" class="btn btn-primary edit-comment" value="수정">
                        <input type="button" class="btn btn-warning delete-comment" value="삭제" data-id="{{ board.id }}">
                    </div>
                    </p>
                <p class="text-right">
                    {{ board.created_at }}
                </p>
            </div>
        </li>
    {% endfor %}
    </ul>
</div>
</div>

<script>
    $('#boardForm').on('submit', function(event){
        event.preventDefault();

        $.ajax({
            url : "{% url 'comment_ajax' %}",
            method : 'POST',
            data : {
                csrfmiddlewaretoken : '{{ csrf_token }}',
                contents : $('#boardInput').val()
            },

            success : function(data){
                $('.list-group').prepend(data)
            }
        });
    });

    $(document).on('click', '.delete-comment', function(){
        $.ajax({
            url : "{% url 'delete_comment_ajax' %}",
            method : 'POST',
            data : {
                csrfmiddlewaretoken : '{{ csrf_token }}',
                id : $(this).data('id')
            },

            success : function(data){
                $('#' + data.id).hide();
            }
        });
    });
</script>
{% endblock %}
```

```html
<!-- list-item.html -->

<li class="list-group-item" id="{{ board.id }}">
    <div class="col-12">
        <p>
            {{ board.contents }}

        <div class="text-right">
            <input type="button" class="btn btn-primary edit-comment" value="수정">
            <input type="button" class="btn btn-warning delete-comment" value="삭제" data-id="{{ board.id }}">
        </div>
        </p>
    <p class="text-right">
        {{ board.created_at }}
    </p>
</div>
</li>
```



## AJAX Response

- 만약 서버에서 처리하고, 아무런 return 해줄 필요 없다면?

```python
def edit_comment_ajax(request):
    if request.method == 'POST' and request.is_ajax():
        id = request.POST['id']
        contents = request.POST['contents']

        board = Board.objects.get(id=id)
        board.contents = contents

        board.save()

        context = {
            'id' : board.id,
            'contents' : board.contents
        }

        return HttpResponse(status=204)
```

- data 속성을 삭제하기 위해서는?

```html
<script>
var id = $('#submitComment').data('id');

$.ajax({
    url : "{% url 'edit_comment_ajax' %}",
    method : 'POST',
    data : {
        csrfmiddlewaretoken : '{{ csrf_token }}',
        id : id,
        contents : contents
	},

    success : function(data){
        $('#' + id + ' .item-contents').text(contents);

        $('#submitComment').removeData('method');
        $('#submitComment').removeData('id');
    }
</script>
```



## VI. Block Script

- 스크립트 관련 공간을 따로 마련해 줄 수 있다.

```html
<!-- base.html -->

<nav class="nav nav-pills nav-fill my-2">
    <a class="nav-item nav-link disabled" href="#" tabindex="-1" aria-disabled="true">INSTAGRAMGRAM</a>
    <a class="nav-item nav-link active" href="#">SIGN IN</a>
    <a class="nav-item nav-link" href="#">SIGN UP</a>
</nav>
{% block content %}
{% endblock %}

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

{% block script %}
{% endblock %}

<!-- 요기다 여기!! -->
```

```html
<!-- index.html -->

{% extends 'base.html' %}
{% block content %}
<div class="container">
    <div class="row mt-4">
        <div class="card col-12">
            <form action="{% url 'articles' %}" method="POST">
                <input type="hidden" name="csrfmiddlewaretoken" value="{{ csrf_token }}">
                <div class="card-body" style="min-height : 8rem;">
                    <textarea class="form-control" rows="5" name="contents"></textarea>
                </div>
                <div class="card-footer text-right">
                    <input type="submit" class="btn btn-success" value="작성하기">
                </div>
            </form>
        </div>
    </div>
</div>

<div class="container">
    {% for article in articles %}
    <div class="row mt-4">
        <div class="card col-12" id="{{ article.id }}">
            <img src="" class="card-img-top">
            <div class="card-body" style="min-height : 8rem;">
                <p class="card-text">
                    {{ article.contents }}
                </p>
                <p class="card-text text-right">
                    {{ article.updated_at }}
                </p>
                <p class="card-text float-right">
                    <span>
                        <button class="btn btn-warning"><i class="fas fa-edit"></i></button>
                        <button class="btn btn-danger"><i class="fas fa-trash-alt"></i></button>
                    </span>
                </p>
            </div>

            <ul class="list-group list-group-flush">
                <li class="list-group-item">
                    <div class="row">
                        <div class="col-9">
                            <input type="text" class="form-control" name="contents" placeholder="댓글을 입력해 주세요">
                        </div>
                        <div class="col-3">
                            <input type="button" class="submit-comment btn btn-primary" value="댓글쓰기" data-article_id="{{ article.id }}">
                        </div>
                    </div>
                </li>

                <div class="comments">
                    {% for comment in article.get_comments %}
                    <li class="list-group-item" id='{{ comment.id }}'>
                        <i class="fas fa-comment-dots mr-2"></i>
                        <span class="item-contents">{{ comment.contents }}</span>

                        <span class="float-right">
                            <span class="item-updated-at">{{ comment.updated_at }}</span>
                            <button class="btn btn-warning edit-comment" data-article_id="{{ article.id }}" data-comment_id="{{ comment.id }}"><i class="fas fa-edit"></i></button>
                            <button class="btn btn-danger delete-comment" data-article_id="{{ article.id }}" data-comment_id="{{ comment.id }}"><i class="fas fa-trash-alt"></i></button>
                        </span>
                    </li>
                    {% endfor %}
                </div>
            </ul>
        </div>
    </div>
    {% endfor %}
</div>
{% endblock %}

<!-- script 에 대한 내용이 여기 들어가게 된다. -->
{% block script %}
<script>
    $(document).ready(function(){
        $(document).on('click', '.submit-comment', function(event){
            event.preventDefault();

            var method = $(this).data('method');

            if (method == 'edit') {
                var article_id = $(this).data('article_id');
                var comment_id = $(this).data('comment_id');
                var contents = $('#' + article_id + ' .form-control').val();

                $.ajax({
                    url : "{% url 'edit_comment' %}",
                    method : 'POST',
                    data : {
                        csrfmiddlewaretoken : '{{ csrf_token }}',
                        article_id : article_id,
                        comment_id : comment_id,
                        comment_contents : contents
                    },

                    success : function(data){
                        $('#' + comment_id + ' .item-contents').text(contents);
                        $('#' + comment_id + ' .item-updated-at').text(data.comment_updated_at);

                        $('#' + article_id + ' .submit-comment').removeData('comment_id');
                        $('#' + article_id + ' .submit-comment').removeData('method');

                        $('#' + article_id + ' .form-control').val('');
                    }
                });
            }

            else {
                var article_id = $(this).data('article_id');
                var contents = $('#' + article_id + ' .form-control').val();

                $.ajax({
                    url : "{% url 'comments' %}",
                    method : 'POST',
                    data : {
                        csrfmiddlewaretoken : '{{ csrf_token }}',
                        article_id : article_id,
                        comment_contents : contents
                    },

                    success : function(data){
                        $('#' + article_id + ' .comments').prepend(data);
                        $('#' + article_id + ' .form-control').val('');
                    }
                });
            }
        });

        $(document).on('click', '.edit-comment', function(){
            article_id = $(this).data('article_id');
            comment_id = $(this).data('comment_id');
            contents = $('#' + comment_id + ' .item-contents').text();

            $('#' + article_id + ' .submit-comment').data('method', 'edit');
            $('#' + article_id + ' .submit-comment').data('comment_id', comment_id);
            $('#' + article_id + ' .form-control').val(contents);
        });

        $(document).on('click', '.delete-comment', function(){
            article_id = $(this).data('article_id');
            comment_id = $(this).data('comment_id');

            $.ajax({
                url : "{% url 'delete_comment' %}",
                method : 'POST',
                data : {
                    csrfmiddlewaretoken : '{{ csrf_token }}',
                    article_id : article_id,
                    comment_id : comment_id
                },

                success : function(data){
                    $('#' + comment_id).hide();
                }
            });
        });
    });
</script>
{% endblock %}
```

