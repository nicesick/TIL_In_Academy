# Android Start

## I. SDK 오류 날 때

- student\AppData\Local\Android\Sdk 파일을 지운다.
- student\AndroidStudio3.5 파일도 지운다.



## II. AVD Manager

- Virtual Device Manager
- 가상의 폰을 만드는 거임
  - Create Virtual Device
  - 5.1'' WVGA
  - Oreo (8.1)
  - RAM : 2048
  - VM heap : 512
  - Internal Storage : 800
  - 실행



## III. SmartPhone

- 빌드 옵션을 여러번 클릭
- 개발자 옵션에서 USB 디버깅을 활성화

- 애니메이션 배율
- 효과 애니메이션 배율 
  - 0.5 로 바꿔보자



## IV. Project 생성

- Empty Activity
- Save location : C:\android\AndroidStudioProjects\Hello
- Language : Java

- Minimum API level : 5.1 (Lollipop)

- Finish



- Maven 보다 더 발전한 게 Gradle 이다.

- 안드로이드에서는 Gradle 씀 : 필요할 때 적재해서 쓰는 거임



## V. 프로젝트

- build.gradle : Maven 의 pom.xml 이랑 똑같은 역할
  - minSdkVersion : 최소 SDK 버전
  - targetSdkVersion : 타겟 SDK 버전
  - dependencies : 외부 라이브러리를 여기에 추가해서 사용할 수 있다.



- java > com.example.hello > MainActivity : 기능을 넣는 곳



- res > layout > activity_main.xml : 화면 디자인을 넣는 곳
- res > mipmap : 시작 아이콘을 넣는 곳
- res > values : 대표적으로 strings.xml > 사실 app 에서 쓰이는 모든 string 은 여기에 있어야만 했다!!
- res > drawable : 이미지들을 넣는 곳



- manifests > AndroidManifest.xml : 프로젝트의 이름, 아이콘, 앱에 대한 설정을 관리하는 부분

  - 더 중요한 부분은 activity 등록을 여기서 해야한다.

  ```xml
  <activity android:name=".MainActivity">
      <intent-filter>
          <action android:name="android.intent.action.MAIN" />
  
          <category android:name="android.intent.category.LAUNCHER" />
      </intent-filter>
  </activity>
  
  <!-- 처음 클릭을 했을 때, MainActivity 부터 실행이 된다. -->
  ```

  

## VI. 이미지

- 이미지 : drawable > v24 안쓴다.
  - activity_main.xml 에서 바꿀 수 있다.
  - ConstraintLayout 에서 이미지를 등록할 거다.
  - backgroud를 클릭하면 이미지를 등록할 수 있다.
  - 색깔을 넣을 수도 있다.



- 아이콘 : mipmap > hdpi 로 쓴다.
  - manifest에서 바꿀 수 있다.



- 버튼 : Common > Button 을 클릭해서 추가할 수 있다.
  - Attributes 에서 속성들을 변화시킬 수 있다.



- 이벤트 : 함수를 만들고, 파라미터에 View 를 넣는다.
  - activity_main.xml 에서 onClick 에서 이벤트를 연결만 해주면 된다.

```java
public void clickButton(View view) {
    Toast.makeText(this, "click button", Toast.LENGTH_SHORT).show();
    // this : 어디에 띄울 거냐
    // "click button" : 뭘 띄울 거냐
    // Toast.LENGTH_SHORT : 길게 띄울 거냐??????
}
```



## VII. 전송

- 핸드폰으로 쏘면
  - java
  - res
  - manifests
- WAR 처럼 묶여서 핸드폰으로 전송되는 거다!

- Build > Generate Signed Bundle : 실제 배포용 앱 생성
- Build > Build Bundle : 디버그용 앱 생성 (출처가 없는 앱)



## VIII. 로그

- 로그는 Logcat 에서 확인할 수 있다.
  - Log.d : Debug
  - Log.i : Info
  - Log.e : Error
- 각자의 창에서 실행 가능하다.

- Edit Filter Configuration 에서 해당 로그만 출력하는 것도 가능하다!
  - Log Tag : [Debug].... 를 추가하면, 해당 Tag 만 보는 건 줄 알았는데..
  - 다른 것도 많이 나왔다..



## X. 앱이 꺼질때?

- Back 버튼을 눌러야 메모리에서 제거된다.
- Home 버튼은... 아직 안 사라짐
  - 그럼 이때 불필요한 것들은 어떻게 처리해야 하나?



- Generate 
  - onCreate : 새로 시작했을 때

  

  - onResume : Home 버튼을 누르고, 다시 시작했을 때
  - onPause : Home 버튼을 눌렀을 때



## XI. 용어

- 위젯 : 액티비티에 들어가는 다양한 버튼, 이미지 등등을 말한다.
  - 이 요소들은 반드시 Layout 안에 들어 있어야 한다.
  - Layout 은 ViewGroup 에 속해있다.
  - ViewGroup 은 View 에 속해있다.



- 위젯 크기
  - match parent
  - wrap content
  - dp



- 레이아웃

  - LinearLayout

    - horizontal
    - vertical

    - 폰 규격이 다 다를 때

  

  - RelativeLayout
  - ConstrainLayout
    - 문제 : 다른 크기의 폰에서는 잘못 보일 수도 있다!
    - 쓸 경우 : 폰 규격이 맞춰져 있을 때
  - TableLayout
    - 테이블처럼 넣을 때



## XII. 위젯 가지고 오기

- ID 를 이용해서 가지고 온다.

```java
// R : 이 프로젝트의 모든 리소스를 관리한다.
public void btclick(View view) {
    Button bt2 = findViewById(R.id.button29);
    bt2.setBackgroundColor(Color.RED);
}
```

- View 안에는 클릭된 위젯 값들을 저장하고 있다.

  - why? 최상위 값은 View 이기 때문엥...

  ```java
  public void clickBtn(View view) {
      if (view.getId() == R.id.btn1) {
  		img.setImageResource(R.drawable.icon1);
          // 이런식으로 이미지를 변경해 줄 수 있다.
      }
  
      else if (view.getId() == R.id.btn2) {
  
      }
  
      else if (view.getId() == R.id.btn3) {
  
      }
  
      else if (view.getId() == R.id.btn4) {
  
      }
  }
  ```



- 만약 FrameLayout 에서 ConstraintLayout 으로 화면을 바꿔주고 싶다면

```java
private void disable() {
    loginLayer.setVisibility(View.INVISIBLE);
    registerLayer.setVisibility(View.INVISIBLE);
}

// 이런 식으로 보이고 안보이고를 결정할 수 있다.
```



## XIII. 위치

- 패딩
  - 주로 패딩을 이용해 조정할 수도 있다
- 마진
- 그래비티
  - center_* 를 사용하면 중간으로 보낼 수 있다.
- 레이아웃 그래비티
  - 전체가 아니라 버튼 하나의 그래비티 속성을 의미한다.

- 웨이트
  - 웨이트를 통해 각 레이아웃의 비율을 맞출 수 있다.



- RelativeLayout
  - layout_alignParentBottom : 바닥에 붙는다.



- TableLayout
  - layout_span