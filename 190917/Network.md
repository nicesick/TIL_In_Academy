# Network

## I. 쓰레드

- 프로세스 여러개 받는 것보다 쓰레드로 만들어지는 게 더 좋다.
- 메모리 효율성이 좋아진다.



- 쓰레드를 생성하는 2가지 방법
  - extends Thread
  - implements Runnable > new Thread(r);



- 쓰레드 우선순위 지정
  - setPriority
  - getPriority



## II. 쓰레드 그룹

```java
ThreadGroup tg1 = new ThreadGroup("TG1");
// 쓰레드 그룹 이름을 지정해주고

new Thread(tg1, r, "Th1").start();
new Thread(tg1, r2, "Th2").start();
// 쓰레드를 만들 때, 그룹에 지정해 줄 수 있다.
// 이렇게 하면 나중에 Priority 조정이라든지 제어하기 편해짐
```



## III. 쓰레드 제어

- setDaemon(true) 를 하면, main 이 죽으면 자식 쓰레드도 같이 죽는다.