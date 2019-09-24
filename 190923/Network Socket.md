# Network Socket

## I. Server , Client

- Server
  - Server Socket 에서의 구성요소
    - ServerSocket : port
    - Socket
    - OutputStream
    - DataOutputStream

- Client
  - Client Socket  에서의 구성요소
    - Socket : ip, port
    - InputStream
    - DataInputStream



## II. 멀티 채팅

- 멀티 채팅에서 신기했던 것
  - Map 을 통해 key : IP, value : DataOutputStream 으로 생성
  - 받을 때에는 Collection<DataOutputStream> col = map.values(); 로 받음
    - Collection 이란 게 되게 매력적이넹



## III. 채팅 연결

- 준비물
  - 채팅 서버, 채팅 클라이언트(자바), 채팅 클라이언트(앱)
  - 웹 서버, 웹