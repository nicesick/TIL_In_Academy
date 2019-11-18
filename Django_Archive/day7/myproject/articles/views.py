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

    return redirect('articles:show', article.id)

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

    return redirect('articles:show', article.id)

def delete(request, id):
    article = Article.objects.get(id=id)
    article.delete()

    return redirect('articles:index')