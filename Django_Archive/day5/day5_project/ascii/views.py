from django.shortcuts import render
import requests

# Create your views here.

def ascii(request):
    url = 'http://artii.herokuapp.com/fonts_list'
    response = requests.get(url)

    fonts = response.text.split('\n')
    
    context = {
        'fonts' : fonts
    }

    return render(request, 'ascii.html', context)

def ascii_make(request):
    text = request.GET['targetText']
    font = request.GET['targetFont']

    url = f'http://artii.herokuapp.com/make?text={text}&font={font}'
    response = requests.get(url)

    print(response.text)

    return render(request, 'ascii_make.html', {'text_lines' : response.text})