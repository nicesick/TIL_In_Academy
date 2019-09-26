# CAN

## I. 설치

- CAN 연결하고 장치 관리자에 들어감
  - CANPro Analyzer
  - RealSYS_USB_Device_Driver 설치
  - 만약 Serial Port 라고만 뜨면 소프트웨어 업데이트를 해서 
  - RealSYS USB CANPro Analyzer Device 로 바꿔줘야 한다.



- CANPro 프로그램을 실행한다.

  - CAN의 일반적인 프로토콜
    - 아이디부 + 명령부 (누구에게 + 무엇을) : Hex + Bit
  - CANPro 환경 설정을 하고, (Serial + Port 설정)
  - 데이타 수신 시작 버튼을 누름

  - 다른 쪽은 송신 데이터 설정을 누름
    - 맞춰야 하는 조건 : CAN BPS - 이게 맞아야함



## II. Java + CAN 통신

- 라이브러리
  - RXTXcomm



- dll 파일
  - rxtxParallel.dll
  - rxtxSerial.dll
  - <JAVA_HOME>/jre/bin 에 넣는다. (이건 jdk 에 넣는거임)
  - 만약 안되면, jre1.8.220/bin 에 넣는다.



- 기본적으로 COM Serial 은 속도가 느리기 때문에,
- 모든 데이터는 처음 시작 데이터와 끝 데이터를 맞추어야만 한다.



```java
package can;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

public class SerialTest implements SerialPortEventListener {
    private BufferedInputStream bin;
    private InputStream in;
    private OutputStream out;
    private SerialPort serialPort;
    private CommPortIdentifier portIdentifier;
    private CommPort commPort;

    public SerialTest() {
    }

    public SerialTest(String portName) throws NoSuchPortException {
        portIdentifier = CommPortIdentifier.getPortIdentifier(portName);
        System.out.println("Connect Com Port!");

        try {
            connectSerial();
            System.out.println("Connect OK !!");
            (new Thread(new SerialWriter())).start();
        } catch (Exception e) {
            System.out.println("Connect Fail !!");
            e.printStackTrace();
        }
    }

    private class SerialWriter implements Runnable {
        String data;

        public SerialWriter() {
            this.data = ":G11A9\r";
        }

        public SerialWriter(String serialData) {
            // W28 00000000 000000000000
            // :W28 00000000 000000000000 53 \r
            String sdata = sendDataFormat(serialData);
            System.out.println(sdata);
            this.data = sdata;
        }

        public String sendDataFormat(String serialData) {
            serialData = serialData.toUpperCase();
            char c[] = serialData.toCharArray();
            int cdata = 0;
            for (char cc : c) {
                cdata += cc;
            }
            cdata = (cdata & 0xFF);

            String returnData = ":";
            returnData += serialData + Integer.toHexString(cdata).toUpperCase();
            returnData += "\r";
            return returnData;
        }

        public void run() {
            try {
                byte[] inputData = data.getBytes();
                out.write(inputData);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void connectSerial() throws Exception {
        if (portIdentifier.isCurrentlyOwned()) {
            System.out.println("Error: Port is currently in use");
        }

        else {
            commPort = portIdentifier.open(this.getClass().getName(), 5000);

            if (commPort instanceof SerialPort) {
                serialPort = (SerialPort) commPort;
                serialPort.addEventListener(this);
                // Serial 로 부터 데이터 이벤트가 발생하면 그 때 데이터를 받기 위함

                serialPort.notifyOnDataAvailable(true);
                serialPort.setSerialPortParams(921600, // 통신속도
                                               SerialPort.DATABITS_8, // 데이터 비트
                                               SerialPort.STOPBITS_1, // stop 비트
                                               SerialPort.PARITY_NONE); // 패리티

                in = serialPort.getInputStream();
                bin = new BufferedInputStream(in);
                out = serialPort.getOutputStream();
            }

            else {
                System.out.println("Error: Only serial ports are handled by this example.");
            }
        }
    }

    public void sendMsg(String msg) {
        // W28 00000000 000000000000
        SerialWriter sw = 
            new SerialWriter(msg);
        new Thread(sw).start();
    }

    public static void main(String[] args) {
        try {
            SerialTest serialTest = new SerialTest("COM7");
            String msg = "W28"
            // cmd
                + "00000000"
            // ID
                + "00000000"
                + "00000000";
           	// Data
            
            serialTest.sendMsg(msg);
        } catch (NoSuchPortException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        switch (event.getEventType()) {
            case SerialPortEvent.BI:
            case SerialPortEvent.OE:
            case SerialPortEvent.FE:
            case SerialPortEvent.PE:
            case SerialPortEvent.CD:
            case SerialPortEvent.CTS:
            case SerialPortEvent.DSR:
            case SerialPortEvent.RI:
            case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
                break;
            case SerialPortEvent.DATA_AVAILABLE:
                byte[] readBuffer = new byte[128];

                try {
                    while (bin.available() > 0) {
                        int numBytes = bin.read(readBuffer);
                    }

                    String ss = new String(readBuffer);

                    if (checkSerialData(ss)) {
                        System.out.println("Receive Low Data:" + ss + "||");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    public boolean checkSerialData(String data) {
        boolean check = false;
        // :U2800000050000000000000002046
        String checkData = data.substring(1, 28);
        String checkSum = data.substring(28, 30);

        char c[] = checkData.toCharArray();
        int cdata = 0;
        for (char cc : c) {
            cdata += cc;
        }
        cdata = (cdata & 0xFF);
        String serialCheckSum = Integer.toHexString(cdata).toUpperCase();
        if (serialCheckSum.trim().equals(checkSum)) {
            check = true;
        }
        
        return check;
    }
}

```

- 받은 내용 : Receive Low Data :U280000000000000000000000003F
  - :U28 - 시작하겠다 (받았다) - 만약 보냈다는? :W28 (보냈다)
  - 00000000 - 송신 ID
  - 0000000000000000 - 송신 데이터
  - 3F - 체크섬