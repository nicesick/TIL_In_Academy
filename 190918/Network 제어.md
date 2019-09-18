# Network 제어

## I. 쓰레드 제어 함수

- sleep
- interrupt
  - interrupt()
  - interrupted()
  - isInterrupted()



- 주의할 점
  - sleep 도중에 interrupt 플래그 신호가 가게 되면, InterruptedException 이 발생하게 된다. 이것까지 생각해 줘야한다!

```java
@Override
public void run() {
    while(!isInterrupted()) {
        try {
            Thread.sleep(1000);
            // sleep 도중에 interrupt 가 오면, exception 으로 간다.
            System.out.println("Inter");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        // 여기서 interrupt 가 오면 무사히 종료된다.
    }

    System.out.println("Inter END...");
}
```



- Thread.currentThread()
  - 현재 쓰레드의 정보가 들어있다.
  - getId()
  - getName()



- suspend
- resume
- stop
  - 이 세 함수는 일시정지, 다시 시작, 정지이지만 데드락 문제 때문에 
    쓰면 안된다고 한다.
  - 또한 suspend 함수를 쓰면 생기는 문제가 main Thread 조차도 같이 멈추는 현상이 생겼다.
  - **결론 : 이것들 쓰지마라** 대신에 interrupt 로도 충분히 할 수 있다.
  - 아니면 플래그를 따로 만들어서 나올 수 있는 길을 만들어라

```java
class Sus2 implements Runnable {
    private boolean isSuspend;
    private boolean isStop;
    // 이렇게 플래그를 만드는게 좋다.

    public Sus2() {
        isSuspend = false;
        isStop = false;
    }

    public void setStop() {
        isStop = !isStop; 
    }

    public void setSuspend() {
        isSuspend = !isSuspend;
    }

    @Override
    public void run() {
        while(!isStop) {
            if (!isSuspend) {
                System.out.println(Thread.currentThread().getId() + " " + Thread.currentThread().getName());
            }
        }
    }
}
```



- join : 해당 쓰레드가 끝날 때까지 기다린다!



## II. 동기화

- synchronized 키워드와 wait, nofity를 이용해서 동기화 시킨다!
  - synchronized : 쓰레드가 하나씩 접근 가능?
  - wait : 
  - nofity :  