# Java Exception 에 대해서

## I. throws new Exception("some Error Message");

```java
// makeAccount function
if (accountName.length != 5) {
    throws new Exception("E1000");
}
```



```java
// main function
try {
    makeAccount(accountName);
}
catch (Exception e) {
    sysout(e.getMessage());
}
```



- 밖에서 catch 로 Exception e 를 받았을 때, e.getMessage(); 를 하면 some Error Message 의 값을 받아올 수 있다.
- 에러 메세지는 한국어, 일본어, 중국어 나중에 확장시킬 때 어려움이 있으므로, "E1000" 이런 식으로 만든다.



## II. class variable (== static variable)

- 스태틱 변수는 메모리의 **메소드 영역** 에 들어간다.
- 스태틱 변수는 **Account.cnt** 같이 인스턴스 형성을 하지 않고도 사용할 수 있다.