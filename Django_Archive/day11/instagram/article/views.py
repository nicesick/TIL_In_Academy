from django.shortcuts import render, redirect
from django.http import HttpResponse
from .models import Article, Comment

import json

# Create your views here.

def index(request):
    if request.method == 'POST':
        article = Article()
        article.contents = request.POST['contents']
        article.save()

        return redirect('articles')
    else:
        articles = Article.objects.all().order_by("created_at").reverse()

        context = {
            'articles' : articles
        }

        return render(request, 'index.html', context)

def comments(request):
    if request.method == 'POST' and request.is_ajax():
        contents = request.POST['comment_contents']
        article_id = request.POST['article_id']

        comment = Comment()
        comment.contents = contents
        comment.article_id = article_id
        comment.save()

        article = Article.objects.get(id=article_id)

        context = {
            'article' : article,
            'comment' : comment
        }

        return render(request, 'list-item.html', context)

def delete_comment(request):
    if request.method == 'POST' and request.is_ajax():
        article_id = request.POST['article_id']
        comment_id = request.POST['comment_id']

        article = Article.objects.get(id=article_id)
        article.delete_comment(comment_id)

        return HttpResponse(status=204)

def edit_comment(request):
    if request.method == 'POST' and request.is_ajax():
        article_id = request.POST['article_id']
        comment_id = request.POST['comment_id']
        contents = request.POST['comment_contents']

        article = Article.objects.get(id=article_id)
        article.edit_comment(comment_id, contents)

        comment = article.get_comment(comment_id)
        comment_updated_at = comment.updated_at.strftime("%Y-%m-%d")

        context = {
            'comment_updated_at' : comment_updated_at
        }

        return HttpResponse(json.dumps(context), content_type='application/json')

def edit(request, article_id):
    article = Article.objects.get(id=article_id)

    if request.method == 'POST':
        article.contents = request.POST['contents']
        article.save()

        return redirect('articles')
    else:
        context = {
            'article' : article
        }

        return render(request, 'article/edit.html', context)

def delete(request, article_id):
    article = Article.objects.get(id=article_id)
    article.delete()

    return redirect('articles')