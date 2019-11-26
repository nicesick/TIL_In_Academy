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

def edit_comment_ajax(request):
    if request.method == 'POST' and request.is_ajax():
        id = request.POST['id']
        contents = request.POST['contents']

        board = Board.objects.get(id=id)
        board.contents = contents
        
        board.save()

        return HttpResponse(status=204)