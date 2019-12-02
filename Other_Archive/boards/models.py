from django.db import models
from faker import Faker

# Create your models here.

class Article(models.Model):
    title = models.CharField(max_length=300)
    keyword = models.CharField(max_length=50)
    email = models.CharField(max_length=200)

    contents = models.TextField()

    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    @classmethod
    def dummy(cls, n):
        f = Faker()

        for i in range(n):
            cls.objects.create(
                title=f.text(20),
                keyword=f.company(),
                email=f.email(),
                contents=f.text()
            )

class Comment(models.Model):
    contents = models.TextField()

    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)