# Component 모델

## I. Tomcat

- Component 모델은 Web Server + Container + Servlet 으로 이루어져 있다.
- 단, Container 가 네트워크와 관련된 베이스적 요소들을 지원해 주는 대신에
- Container 에 필요한 규칙에 맞춰서 코드를 짜야 한다.
- 우리는 이에 맞춰서 Servlet 을 짜는 거다!!



- Spring 과 Android 도 비슷한 Component 모델이다 :)



## II. JSP

- jsp 파일 안에 있는 script 에서도 ${} 가 사용 가능 하다.

```jsp
$('button').eq(0).click(function(){
	var c = confirm('Are you Sure to delete yours??');

    if (c) {
   	 location.href='ask?type=user&cmd=delete&id=${userDetail.id }';
    }
});
```



- 또한 요청을 하고 나서 다른 요청을 해야 할 때에는 sendRedirect 를 쓴다.

```java
else if (cmd.equals("delete")) {
    String id = request.getParameter("id");

    try {
        biz.remove(id);
        response.sendRedirect("ask?type=user&cmd=list");
        return;
    } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}
```



## III. Servlets API

- www. servlets.com
- 여기서 cos-20.08.zip 다운
- 이 API 는 파일을 업로드 할 수 있도록 하는 API를 제공한다.



## IV. DB SEQUENCE

- 데이터베이스에서 id 값을 순차적으로 올리고 싶을 때

```sql
CREATE SEQUENCE PSEQ INCREMENT BY 1 START WITH 100;
```

```sql
INSERT INTO T_PRODUCT VALUES (PSEQ.NEXTVAL, 'K1', 1000, SYSDATE, 'a.jpg');
```

```sql
DROP SEQUENCE PSEQ;
```



## V. 파일 서버로 전송하기

- 서버로 보낼 때는 몇가지 조건이 있다.
  - form 에 enctype="multipart/form-data"
  - 등록할 파일은 <input type="file" 로 등록할 수 있다.

```html
<form action="ask?type=product&cmd=add" method="POST" enctype="multipart/form-data">
    NAME <input type="text" name="name"><br>
    PRICE <input type="number" name="price"><br>
    IMG <input type="file" name="imgname"><br>
    <input type="submit" value="SUBMIT">
</form>
```

- 요청을 보내고 나서, Servlet 은 MultipartRequest 를 이용한다.

```java
else if (cmd.equals("addimpl")) {
    MultipartRequest mr = new MultipartRequest(request, "C:\\LimJi\\day1333\\web\\img", 1024*1024*100,"UTF-8");
    // 이 때 사진이 이미 업로드 된다.
    
    String name = mr.getParameter("name");
    String price = mr.getParameter("price");
    String imgname = mr.getOriginalFileName("imgname");
    // 이미지 이름을 얻어올 때에는 이 함수를 쓴다.
}
```



- 파일이 등록외었는지 확인하려면
- Window -> Preferences -> General -> Workspace -> Refresh using native hooks or polling 을 체크!



- 수정할 시
- 만약 사용자가 사진을 안 넣었을 때, null 값으로 오류가 날 수 있으므로

```jsp
<p>
    <img src="img/${pUpdate.imgname }"><br>
    <input type="file" name="newimgname">
    <input type="hidden" name="imgname" value="${pUpdate.imgname }">
    // 히든을 이용해서 값을 유지할 수 있다.
</p>
```

