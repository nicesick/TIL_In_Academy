# HTML, CSS

## I. ID, Classifier

- 태그를 구분할 수 있는 방법으로는 2가지가 있다.

  - ID : # 으로 표현
  - Class : . 으로 표현

- 알고보니 더 있었다.

  - 속성 선택자 : 

  ```css
  input[type="text"] {
      background : yellow;
  }
  ```

  - 직계 자식 선택자 : 

  ```css
  #div2 > div > h3 {
      color : blue;
  }
  ```

  - 후손 선택자 : #div  밑의 div 밑의 h3 를 한번에 지정할 수 있다.

  ```css
  #div2 h3 {
      color : yellow;
  }
  ```

  

## II. Comment

- Cnt + Shift + ?
  - HTML : <?-- -->
  - CSS : /* */



## III. CSS 외부 파일 참조

```html
<link type="text/css" rel="stylesheet" href="c1.css">
```

## IV. CSS TAG

```css
<style>
a {
	text-decoration : none; // 텍스트의 default 환경을 없앤다.
    color : black;
}

a:hover {					// a 에 커서를 갔다 댔을 때
    background : blue;
    color : yellow;
    font-size : 1.5em;
}

input:focus {				// input에 포커스 되어 있을 때
    background : blue;
    color : yellow;
    font-size : 1.5em;
    font-family : "Courier New", Times, serif;
}

h3:nth-child(2n) {			// h3 들 중 짝수 번째 일 때
    color : red;
}
</style>
```

- hover
- focus
- nth-child
- not



## V. Fonts

- Google Fonts 에 들어가서 폰트 선택
- 순서에 따라 사용
  - Standard : HTML 파일에서 사용할 때,
  - @import : CSS 파일에서 사용할 때,



- font-family
- font-size
  - px
  - em
  - %
- font-weight
  - normal
  - bold



## VI. TEXT

- line-height : 
  - 인라인 방식은 텍스트가 차지하는 공간만을 잡아먹는다.
  - 하지만 텍스트 이외의 공간도 잡아먹고 싶을때,
  - 이 속성을 사용한다.

```css
<style>
    a {
        line-height : 100px;
    }
</style>
```

- text-align
  - center
- text-decoration
  - none
  - overline
  - line-through
  - underline



## VII. BLOCK TYPE

- p : Paragraph 형식은 블락 형식이기 때문에 margin 이 존재한다.

  ```css
  <style>
  p {
      margin : 0;			// margin 을 없앨 수 있다.
  }
  
  div {
      margin : 50px;		// margin 을 지정할 수도 있다.
      padding : 50px;	
  }
  </style>
  ```
  - margin : 바깥쪽 여백
  - padding : 안쪽 여백
    - 주의 할 점 : padding 을 쓰게 되면 상자의 크기가 달라질 수도 있다.
    - 따라서, 안의 내용물의 margin 을 키우는 게 더 좋다?



- 모든 css 에는 기본 default 값이 있다.

  - *을 이용해서 margin, padding 값을 초기화 시키는 것을 먼저 진행한다.

- BLOCK TYPE 을 중앙으로 놓기 위해, 주로 margin 값을 사용한다.

  ```css
  div {
      margin : auto;				// 중앙에 맞도록 자동으로 정렬
  }
  ```

- 가끔씩 박스 형태와 인라인 형태가 섞였을 때에는 margin 을 쓰더라도 의도대로 되지 않을 때가 있다.

  ```css
  div > a {
      display : block;			// 인라인 형식을 블락 형태로 변환
      margin : 50px;
  }
  ```



## VIII. BACKGROUND

- background 이미지도 CSS 로 지정할 수 있다.

  ```css
  body {
      background-image : url('img/img0001.jpg');
      background-size : 300px 300px;
      background-repeat : no-repeat;
      background-attachment : fixed;
      background-position : right top;
  }
  ```



- list 를 사용할 때에 가로로 만들 수 있다.

  ```css
  <style>
  li {
      display : inline;			// 박스 형태에서 인라인 형태로 변환
  }
  </style>
  ```

  