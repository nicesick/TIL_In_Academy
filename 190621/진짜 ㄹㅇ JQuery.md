# 진짜 ㄹㅇ JQuery

## I. jQuery : JavaScript 를 좀 더 간략하게? 만든 것

- Brower 상에서 JQuery Plug-In 을 설치한 뒤, 그 위에서 가동



## II. 설치 - 첫번째

- www. jquery.com 에서 jQuery Download 를 다른 링크로 저장
- 해당 프로젝트의 web 폴더에 jquery 폴더를 만들고
- 거기에 저장
- jQuery 탑재

```javascript
<script src="jquery/jquery.min.js"></script>			
// 스크립트에 jquery 탑재

<script>
	// 여기서 스크립트 사용
</script>

```



## II. 설치 - 두번째

- 이 방식은 CDN 방식이라고 함
- src 에 구글, 마이크로소프트에 저장된 jQuery 파일을 가지고 오는 식

```javascript
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

// 구글에서 제공하는 jQuery
```



## III. 사용

```javascript
<script>
$().text();				// JQuery 는 $ 로 시작한다.

$(document).ready(function(){
    $('h1').text();
    $('#hh').html('<a href="">CLICK</a>');			
    							// 여기는 태그도 포함 시킬 수 있따.
    $('.ch').text();
    $('input[type="text"]').css('background', 'gray');
    $('input[name="pwd"]').css({
        				// Object 를 이용해서 여러개를 넣을 수도 있따.
        'color' : 'red',
        'background' : 'blue'
    });
    
    $('h1').eq(0).css('color', 'red');
    $('h1').not($('h1').eq(0)).css('color', 'red');
    							// not 과 eq 는 중요하다!!
    							// 뒤의 내용을 .eq(0) 처럼 뺄 수 있다
								// document 가 준비가 되면
								// function 을 실행해라!
    
    $('a:first').click(function(){
        $('button').hide();
    });
    
    $('a:last').click(function(){
        $('button').show();
    });
    
    $('a').mouseenter();
    $('a').mouseleave();
    
    $('a').hover();				// hover 는 mouseenter + 										// mouseleave 이기 때문에 function 								  // 2개가 들어간다.
    
    $('a').hover(function(){
        $('h1').text('Mouse Enter');
    }, function(){
        $('h1').text('Mouse Leave');
    });
    
    $('input').on({
        focus : function(){						// 커서가 들어올 때
            $(this).css('background', 'red');
        },
        blur : function(){						// 커서가 나올 때
            $(this).css('background', 'white');
        }
        
        keydown : function(){					// 키보드 입력
        	alert($(this).val());
    	}
    
    	keyup : function(){
            alert($(this).val());
        }
    });			// 여러개의 이벤트를 넣고 싶을 땐 on

	$('a').click(function(){
		var id = $('#rf > input[name="id"]').val();
        // 이런식으로 form 안의 정보에 쉽게 접근할 수 있다.
        // id 가 없다 하더라도!!
        
        var pwd = $('#rf > input[name="pwd"]').val();
        var name = $('#rf > input[name="name"]').val();

        var c = confirm('Are you Sure??');

        if (c) {
            $('#rf').attr('method','GET');		// form control
            $('#rf').attr('action', 'a.jsp');
            $('#rf').submit();
        }
	});
});
</script>
```



- 이미지 show, hide, toggle, animate

```javascript
$('button').click(function(){
    $('img').show('slow');
    $('img').hide('slow');
    $('img').toggle();
    $('img').animate({
        left : '300px',
        opacity : '0.5',
        width : '250px'
    });
});
```

- append, prepend, before, after, remove, empty

```javascript
$(document).ready(function(){
    $('button').eq(0).click(function(){
        $('div').append('HI : ' + cnt++);
    });

    $('button').eq(1).click(function(){
        $('div').prepend('HELLO : ' + cnt++);
    });

    $('button').eq(2).click(function(){
        $('div').after('HHEEE : ' + cnt++);
    });

    $('button').eq(3).click(function(){
        $('div').before('HHHOOO : ' + cnt++);
    });

    $('button').eq(4).click(function(){
        $('div').remove();
    });

    $('button').eq(5).click(function(){
        $('div').empty();
    });
});
```

- addClass, removeClass

```javascript
$('button').eq(6).click(function(){
    $('button').eq(0).addClass('dd');
});

$('button').eq(7).click(function(){
    $('button').eq(0).removeClass('dd');
});

// 미리 저장된 CSS 값 hh, dd의 클래스를 클릭했을 때 지정
// 이벤트 이후 디자인을 바꿀 수 있으므로 매우 유용!
```

- jQuery 에서의 for 문

```javascript
var d = [4, 1, 2, 3, 5, 6];

$(document).ready(function(){
    $(d).each(function(idx, item){			// 신기한 방법!!
        alert(idx + ' : ' + item);
    });
});
```

