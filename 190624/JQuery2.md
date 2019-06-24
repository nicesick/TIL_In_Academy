# JQuery & AJAX

## I. Skills

- Document 와 Window 높이 차이를 이용해서 스크롤을 내리면 사진을 계속 받아오는 네이버 이미지처럼 만들어 볼 수 있다.

```javascript
$(window).scroll(function(){
    var scrollTop = $(window).scrollTop();
    var docH = $(document).height();
    var winH = $(window).height();

    console.log(scrollTop);
    console.log(docH);
    console.log(winH);
    
    if (docH <= winH + scrollTop + 50) {
        getData();
    }
});
```



## II. AJAX

- 서버와 데이터를 교환하는 기술의 하나 : 페이지가 바뀌지 않고, 데이터만 받아온다.



## III. 서버 side

- Java Resources 에서 src 에서 New -> Servlet 생성
- Class Name 설정
- URL Mapping 설정

- service 에 체크



## IV. Servlet 이 안될 때

- Properties -> Java Build Path -> Apache Tomcat 이 있는지 확인

- 없으면 Add Library -> Server Runtime -> Apache Tomcat v9.0 설정

- 보통 안되면 Apache Tomcat 이나 JRE System Library 가 문제임



## V. 사용(전통적)

- html 페이지로 연결해 주는 방식

```java
/**
	 * @see HttpServlet#service(HttpServletRequest request, 	HttpServletResponse response)
	 */
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub
    System.out.println("Login Servlet Calls");

    String id = request.getParameter("id");
    String pwd = request.getParameter("pwd");
    
    if (id.equals("aaa") && pwd.equals("111")) {
        response.sendRedirect("loginok.html");
    }
    else {
        response.sendRedirect("loginfail.html");
    }
}
```

- Writer 를 통해 직접 써주는 스타일

```java
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String num1 = request.getParameter("num1");
        String num2 = request.getParameter("num2");

        int n1 = Integer.parseInt(num1);
        int n2 = Integer.parseInt(num2);

        int result = n1 + n2;

        Writer out = response.getWriter();
        out.write(result);

        out.close();
    }
```



## VI. 사용(AJAX)

- 가장 기본적인 방식

```java
<script>
	function display(result) {
		$('h1').text(result);
	};

	function sendData(num1, num2) {
		var surl = 'calc1';

		$.ajax({						// 이렇게 하면 보낼 수 있다
			url : surl,
			method : 'post',
			data : {
				"num1" : num1,
				"num2" : num2
			},

			success : function(result){
				display(result);
			},

			error : function(result) {

			}
		});
	};

	$(document).ready(function(){
		$('button').click(function(){
			var num1 = $('input[name="num1"]').val();
			var num2 = $('input[name="num2"]').val();

			sendData(num1 , num2);
		});
	});
</script>
```

```java
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO Auto-generated method stub

        String num1 = req.getParameter("num1");
        String num2 = req.getParameter("num2");

        int n1 = Integer.parseInt(num1);
        int n2 = Integer.parseInt(num2);

        int result = n1 + n2;

        System.out.println(result);
        Writer out = resp.getWriter();
        out.write(result+"");						// AJAX 에서는 값만 보내주면 된다.
        out.close();
    }
```



- 외부 라이브러리 사용
- Dynamic Web Project 에서는 web -> WEB-INF -> lib 에 복사해 붙여 넣으면 된다.



- json-lib-2.4-jdk15.jar 파일 해당 파일에 복사 붙여넣기

```java
protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // TODO Auto-generated method stub

    response.setCharacterEncoding("UTF-8");		// 한글이 깨지지 않는다.
    PrintWriter out = response.getWriter();

    JSONArray ja = new JSONArray();					// JSON 형식으로 만드는 방법
    JSONObject jo;
    for (int i = 0 ; i < 5 ; i ++) {
        jo = new JSONObject();

        jo.put("rank", i + 1);
        jo.put("keyword", "임지훈 :)" + i);
        jo.put("type", "up");
        jo.put("cnt", 22);

        ja.put(jo);
    }

    out.print(ja.toString());
    out.close();
}

// in HTML
function getData() {
		$.ajax({
			url : 'chart',

			success : function(result){
				alert(typeof(eval(result)));			// eval 을 써줘야 Object 로 변경된다.
				//display(result);
			}
		});
	};
```

