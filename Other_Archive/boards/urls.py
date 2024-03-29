"""django_advance URL Configuration

The `urlpatterns` list routes URLs to views. For more information please see:
    https://docs.djangoproject.com/en/2.2/topics/http/urls/
Examples:
Function views
    1. Add an import:  from my_app import views
    2. Add a URL to urlpatterns:  path('', views.home, name='home')
Class-based views
    1. Add an import:  from other_app.views import Home
    2. Add a URL to urlpatterns:  path('', Home.as_view(), name='home')
Including another URLconf
    1. Import the include() function: from django.urls import include, path
    2. Add a URL to urlpatterns:  path('blog/', include('blog.urls'))
"""

from django.urls import path
from . import views as boards_views

app_name = 'boards'

urlpatterns = [
    path('', boards_views.article_list, name='article_list'),
    path('<int:article_id>/', boards_views.article_detail, name='article_detail'),
    path('new/', boards_views.new_article, name='new_article'),
    path('edit/<int:article_id>', boards_views.edit_article, name='edit_article')
]
