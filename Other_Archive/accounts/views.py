from django.shortcuts import render, redirect

from django.contrib.auth import login as auth_login
from django.contrib.auth import logout as auth_logout

from .forms import CustomUserCreationForm, CustomAuthenticationForm

# Create your views here.

def login(request):
    if request.user.is_authenticated:
        return redirect('boards:article_list')

    if request.method == 'POST':
        form = CustomAuthenticationForm(request, request.POST)

        if form.is_valid():
            user = form.get_user()
            auth_login(request, user)

            return redirect(request.GET.get('next') or 'boards:article_list')
    else:
        form = CustomAuthenticationForm()

    context = {
        'form' : form
    }

    return render(request, 'accounts/login.html', context)

def signup(request):
    if request.user.is_authenticated:
        return redirect('boards:article_list')

    if request.method == 'POST':
        form = CustomUserCreationForm(request.POST)

        if form.is_valid():
            user = form.save()
            auth_login(request, user)

            return redirect('boards:article_list')
    else:
        form = CustomUserCreationForm()

    context = {
        'form' : form
    }

    return render(request, 'accounts/signup.html', context)


def logout(request):
    auth_logout(request)

    return redirect('boards:article_list')