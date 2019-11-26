"""instagram URL Configuration

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
from . import views as article_views

urlpatterns = [
    path('', article_views.index, name='articles'),

    path('<int:article_id>/edit', article_views.edit, name='edit'),
    path('<int:article_id>/delete', article_views.delete, name='delete'),

    path('comments/', article_views.comments, name='comments'),
    path('comments/delete', article_views.delete_comment, name='delete_comment'),
    path('comments/edit', article_views.edit_comment, name='edit_comment')
]