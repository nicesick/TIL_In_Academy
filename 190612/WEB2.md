# WEB2

## I. 하이퍼링크

- a : 다른 소스로 이동, 혹은 해당 위치로 이동, 혹은 파일을 다운!!
  - href : 소스를 입력
  - target : 페이지를 열 때 옵션
    - _blank : 새 페이지에서 열기, 하지만 안 쓴다.

- id : 엘리먼트에 아이디를 지정
  - a 를 이용해서 해당 위치로 이동할 수 있다.	
  - #h_target : 앞에 #을 붙여준다.



## II. CSS, SCRIPT

- head 에서 실행한다.
  - <'style> : CSS
  - <'script> : JavaScript
- display : block; : 해당 태그를 블락 형식으로 바꾸어 준다.
- table > tbody > tr : 하위태그로 내려갈 수 있다.

## III. SCRIPT

- alert : 창 띄우기
- location.href : 이동!!



## IV. HTML TAG

- table
  - caption : 하지만 잘 쓰지 않는다.
  - thead
    - tr
    - th
  - tbody
    - tr
    - td
  - colspan, rowspan : 셀이 차지하는 크기를 조정

- audio
  - src
  - controls
- video
  - src
  - controls

- span

- form

  - action

  - method : GET or POST

  - input : 몇몇은 name 과 value 가 존재해야 한다.

    - text
    - password
    - submit
      - 스크립트를 이용한 방법도 존재
      - onclick="login(this.form);" : 버튼을 눌렀을 때, form에 있는 정보를 보내 login 진행

    

    ```javascript
    function login(f) {
      	var c = confirm('Are you sure ...');
        
      	if (c == true) {
            f.method = 'GET';
            f.action = 'a';
            f.submit();
        }
    };
    ```

    

    - reset

    - radio

    - checkbox

    - select

      - option

    - file : form 형식에 enctype="multipart/form-data" 를 추가해야만 한다.

    - required : 해당 칸을 채우지 않으면 submit 되지 않는다.

    - readonly : 해당 칸을 채울 수 없다, 또한 value 값이 있어야 한다.

      

  - label : radio 와 같이 쓰인다.

    - for : id 를 통해 지정해 줄 수 있다.

  - hidden

- fieldset

  - legend