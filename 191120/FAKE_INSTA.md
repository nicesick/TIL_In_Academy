# Instagram

## I. Relation

- 1 : N
  - 한 명의 사용자가 여러 개의 게시 글을 작성
- M : N
  - ???



## II. Font-Awesome

- fontawesome.com
- 예쁜 폰트 찾기
- 예쁜 아이콘 찾기



## III. FAKE INSTAGRAM 만들기

- 댓글 테이블 작성

```python
# models.py

from django.db import models

# Create your models here.

class Article(models.Model):
    contents = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

class Comment(models.Model):
    contents = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    article = models.ForeignKey(Article, on_delete=models.CASCADE)
    
    # foreignkey 를 지정해주자@!!
    # 이렇게 해도 article 에 접근할 때에는 article_id 로 지정된다!!
```

- Comment 를 가지고 오는 방법은?

```html
<!-- index.html -->

{% for comment in article.comment_set.all %}
<!-- 아니 이게 어떠헥 가능해ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ -->

    <li class="list-group-item">
    <i class="fas fa-comment-dots mr-2"></i>
    {{ comment.contents }}

    <span class="float-right">
    <a class="btn btn-warning"><i class="fas fa-edit mr-2"></i>수정</a>
    <a href="{% url 'delete_comment' comment.id %}" class="btn btn-danger"><i class="fas fa-trash-alt mr-2"></i>삭제</a>
    </span>
    </li>
{% endfor %}
```

- 파일 나누기

  - article/edit.html
  - comment/edit.html
  - 만들어주면 쉽게 구분할 수 있다!!
  - 접근은?

  ```python
  return render(reuqest, 'article/edit.html')
  # 이렇게 구별 가능!!!
  ```

- 만약 Comment 가지고 오는 거에서 가독성을 원한다면?

```python
# models.py

from django.db import models

# Create your models here.

class Article(models.Model):
    contents = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    def get_comments(self):
        return Comment.objects.filter(article_id=self.id)

class Comment(models.Model):
    contents = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    article = models.ForeignKey(Article, on_delete=models.CASCADE)
```

- index.html

```html
<!-- index.html -->

{% for comment in article.get_comments %}
<li class="list-group-item">
    <i class="fas fa-comment-dots mr-2"></i>
    {{ comment.contents }}

    <span class="float-right">
        <span>{{ comment.updated_at }}</span>
        <a href="{% url 'edit_comment' comment.id %}" class="btn btn-warning"><i class="fas fa-edit"></i></a>
        <a href="{% url 'delete_comment' comment.id %}" class="btn btn-danger"><i class="fas fa-trash-alt"></i></a>
    </span>
</li>
{% endfor %}
```