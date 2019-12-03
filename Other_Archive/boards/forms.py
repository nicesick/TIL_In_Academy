from django import forms
from .models import Article, Comment

class ArticleForm(forms.ModelForm):
    email = forms.EmailField()
    title = forms.CharField(min_length=2)

    class Meta:
        model = Article
        exclude = ('datetime',)