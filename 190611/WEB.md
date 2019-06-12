# WEB

## I. Web Client Application

- HTML
- CSS
- JavaScript : 공식 -> 아류 - JQuery : 비공식



## II. Web Server Application

- Java



## III. 이번에 배우는 건 Web Client Application

- Java EE (Enterprise Edition)

  

- Tomcat

  - 설치 : Servers -> Apache -> Tomcat v9.0 -> Tomcat 설치 경로 지정 -> finish

  - 포트지정 : HTTP/1.1 - > 80

  - 톰캣 시작 : 스타트 버튼 클릭

    

  - 웹 어플리케이션 프로젝트 시작 : New -> Dynamic Web Project

  - Context root : 어플리케이션 이름

  - Content directory : html 이 생기는 디렉토리

    

  - day01 을 Tomcat 서버로 드래그 or 서버 오른쪽 클릭 Add and Remove 선택 : 해당 프로젝트 추가 및 제거 가능

  - web 디렉토리에서 html 파일 추가 : HTML 5 로 선택

  - 70.12.50.220:80/day01/a1.html 로 접근



## IV. HTML ELEMENT

- element : 시작 태그 + 콘텐츠 + 엔드 태그
- attribute : 태그 안의 속성
- 주석 : <!-- -->



- 태그 참고 사이트 : www.w3schools.com



## V. Tag

- <'img src="">
- <'p> : 단락 한 줄을 다 잡아먹는다.
- <'br> : 한 줄 띄우기, 하지만 잘 쓰이지 않는다.

- &'nbsp; : 스페이스
- <'pre> : 이 안에서는 스페이스가 인식된다, 하지만 잘 쓰이지 않는다.
- <'h1> : 글씨 크기, p 와 같이 단락 한 줄을 다 잡아먹는다.

- 집합체
  - <'ul>
  - <'li>
  - : CSS 로 한꺼번에 처리할 수 있도록 만들 수 있다.
  - : 또한 줄로 되어 있다 하더라도, CSS 로 한 줄로 만들 수 있다.



- 텍스트 서식은 쓰지 않는다.

  

- 블락 태그 vs 인라인 테그
  - 블락 태그 : 한 줄 다 잡아먹음 ex) p, h1, ul, li
  - 인라인 태그 : 그냥 필요 화면만 잡아먹음 ex) img



- Cnt + Shift + ? : 그 줄 주석