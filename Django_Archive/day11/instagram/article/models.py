from django.db import models

# Create your models here.

class Article(models.Model):
    contents = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)

    def get_comments(self):
        return Comment.objects.filter(article_id=self.id).order_by('created_at').reverse()

    def edit_comment(self, comment_id, contents):
        comment = Comment.objects.get(id=comment_id)
        comment.contents = contents

        comment.save()

    def delete_comment(self, comment_id):
        comment = Comment.objects.get(id=comment_id)
        comment.delete()

    def get_comment(self, comment_id):
        comment = Comment.objects.get(id=comment_id)

        return comment

class Comment(models.Model):
    contents = models.TextField()
    created_at = models.DateTimeField(auto_now_add=True)
    updated_at = models.DateTimeField(auto_now=True)
    article = models.ForeignKey(Article, on_delete=models.CASCADE)