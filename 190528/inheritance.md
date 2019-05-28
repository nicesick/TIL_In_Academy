# inheritance

## I. 상속 유의사항

- static 함수는 클래스 것이기 때문에 오버라이딩 할 수 없다. :)



```java
class TV ...
class CaptionTV extends TV ...
    
public class void main() {
    TV t = new CaptionTV(); // 쌉가능!
}
```

- 조상 타입에 자손 인스턴스를 할당 가능 :)



```java
for (Shape sh : s) {
    sh.move(5, 5);
    
    if (sh instanceof Circle) { // sh 의 인스턴스가 Circle 형식일 경우
        Circle c = (Circle)sh;  // fillColor 함수를 쓰기 위해 형변환
        c.fillColor("red");		// 함수 사용
    }
}
```

- 상속받은 클래스의 고유한 함수가 있으면 이런 식으로 사용할 수 있다 :)

  

- App(화면) ----> Biz(정당성 체크 && 트랜젝션 체크 : 만약 하나라도 에러가 뜨면 원상태로 되돌려 놓음) ---->  Dao(디비 호출) ----> Oracle(디비)
  - 위처럼 한 클래스가 한 기능만을 담당할 수 있도록 설계해준다.
  - 하지만 Biz 와 Dao 는 추상 클래스로 설계를 해서 App 이 Biz 와 Dao 의 자식 클래스를 알지 못하도록 한다.
  - Biz 가 UserBiz 와 ProductBiz 등으로 자식 클래스로 나뉜다.
  - 이 때 App 에서 주로 쓰이는 것 : Object 클래스!



## II. Import 단축키

- cnt + shift + O 를 하면 자동으로 Import 해준다 개꿀! :)



## III. Collection API

- 이 세 가지는 부모 클래스임.. 하위 클래스로 할당해줘야 함

- Set - 중복 불가, 순서 없음
- List - 순서 있음, 속도 느림
- Hash - Key, Value



## IV. DB : 기본적인 CRUD

- Create     - insert
- Read        - select(key), select()
- Update    - update
- Delete     - delete