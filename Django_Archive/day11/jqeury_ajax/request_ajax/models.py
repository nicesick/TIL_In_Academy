from django.db import models

# Create your models here.

class Board(models.Model):
    contents = models.CharField(max_length=16)
    created_at = models.DateTimeField(auto_now_add=True, null=True)