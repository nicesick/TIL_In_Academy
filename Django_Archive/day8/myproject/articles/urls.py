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