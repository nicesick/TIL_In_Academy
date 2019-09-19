# Network Message

## I. PipedWriter , PipedReader

- 두개를 연결해서 메시지를 주고 받을 수 있게 해주는 클래스

```java
// PipedReader

public class InputThread extends Thread{
    PipedReader input;

    public InputThread(String name) {
        super(name);
        input = new PipedReader();
    }

    @Override
    public void run() {
        int data = 0;
        StringWriter sw = new StringWriter();
		// 신기하게도 reader 로부터 받은 데이터는 int 로 받는다.
        // 따라서 이 클래스를 써서 String 으로 만들어 준다.
        
        try {
            while((data = input.read()) != -1) {
                // input.read() 부분에서 메시지가 오기를 기다리고 있다.
                // 오면 그 때 받는다.
                // 이거 int 로 받을 필요없이 BufferedReader로 String 으로 받을 수도 있다.
                sw.write(data);
            }

            System.out.println(getName() + " received : " + sw.toString());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public PipedReader getInput() {
        return input;
    }

    public void connect(PipedWriter output) {
        try {
            input.connect(output);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

```

```java
// PipedWriter

public class OutputThread extends Thread {
    PipedWriter output;

    public OutputThread(String name) {
        super(name);
        output = new PipedWriter();
    }

    @Override
    public void run() {
        String msg = "Hellooooooooooooooooooooooooooo";
        System.out.println(getName() + " sent : " + msg);

        try {
            output.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public PipedWriter getOutput() {
        return output;
    }

    public void connect(PipedReader input) {
        try {
            output.connect(input);
            
            // 둘 다 connect 가 만들어져 있긴 하지만
            // 둘 중 하나만 연결을 시도하면 된다.
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

```

```java
// Main

public class Main {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        InputThread it = new InputThread("InputThread");
        OutputThread ot = new OutputThread("OutputThread");

        ot.connect(it.getInput());
        // 이렇게 하나만 연결시도하면은 된다!

        it.start();
        ot.start();
    }
}
```

- while 문을 이용해서 계속 메세지를 보내려 하였으나, output.close() 하지 않으면 input 이 받지 못했다. > 일회용인 것 같다!



## II. InputStreamReader , OutputStreamWriter

- 강점 : 인코딩을 지정해 줄 수 있다!



## III. File (File~Stream, Buffered~Stream, Object~Stream)

- 왜 꼭 파일시스템에 String 만 보내려 하는가?
  - Object 를 보낼 수도 있잖아

- 단계적으로 쌓는 것들은 가장 큰 것만 close 해주면 나머지는 알아서 해준다.

```java
public static void main(String[] args) throws Exception {
    FileOutputStream fos = new FileOutputStream("user.dat");
    BufferedOutputStream bos = new BufferedOutputStream(fos);
    ObjectOutputStream oos = new ObjectOutputStream(bos);

    User user = new User("id01", "pwd01", 20);

    oos.writeObject(user);
    // user 는 반드시 Serializable 해야 한다.
    oos.close();
}
```

```java
public class User implements Serializable
// 이렇게!!
```

- 읽어올때는?

```java
public static void main(String[] args) throws Exception {
    FileInputStream fis = new FileInputStream("user.dat");
    BufferedInputStream bis = new BufferedInputStream(fis);
    ObjectInputStream ois = new ObjectInputStream(bis);

    User user = (User) ois.readObject();

    System.out.println(user.toString());
    // 똑같이 해주면 된다!
}
```

- 근데 문제가... 비밀번호 같은 거는 보내고 싶지 않을 때!

```java
transient private String pwd;
// transient 를 사용해서 stream 을 통과하지 못하게 만들면 된다!
```



## IV. Network

- 지금까지 파일스트림으로 데이터를 주고 받았다면, 네트워크는 두 컴퓨터 간에 파일스트림을 연결하는 것을 말한다.
- 두 컴퓨터를 연결하는 방법은 여러가지가 있다. : http, ftp, smtp, tcp/ip

- 두 컴퓨터를 연결하고 stream 으로 데이터를 빨아오는 거다.



- 근데 이거 짜는 거 되게 어려운데, Java 에서는 java.net 클래스를 만들어서 쉽게 짤 수 있게 만들었따.
  - InetAddress
  - URL

```java
public static void main(String[] args) throws Exception {
    URL url = null;
    url = new URL("http://70.12.60.90/test");

    URLConnection conn = url.openConnection();
    InputStream in = conn.getInputStream();

    BufferedInputStream bis = new BufferedInputStream(in);
    InputStreamReader isr = new InputStreamReader(bis);

    int data = 0;
    StringWriter sw = new StringWriter();

    while((data = isr.read()) != -1) {
        sw.write(data);
    }

    System.out.println(sw.toString());
}
```

