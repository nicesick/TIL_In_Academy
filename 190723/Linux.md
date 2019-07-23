# Linux

## I. Shell

- *.sh 형식

```shell
ls -al /etc
```

- 실행할 땐 ./ 을 앞에 붙여준다. (현재 디렉토리가 PATH 에 등록되어 있지 않아서?)

```shell
./runls.sh
```



- 등록하려면 .bashrc 에 추가한다.

```shell
PATH=.:$PATH
export PATH
```



## II. 링크

- 하드 링크
  - 심볼릭과 비슷하지만, 원본과 크기까지 똑같다?
  - 비록 원본 파일이 사라져도 하드 링크는 사라지지 않는다.
- 심볼릭 링크
  - 윈도우의 바로가기와 같다.
  - 원본 파일이 사라지면 링크는 끊어진다.



```shell
ln linktest/ltest hlink : 하드링크
ln -s linktest/ltest slink : 심볼릭링크
```



## III. COMMANDs

- which
- find /etc -name *.conf
- find /usr/bin -perm 666
- find /usr/bin -size +10k -size -100k
- find ~ -size 0k -exec ls -l {} \;

```shell
-exec(시작) \;(끝)
-exec cp {} temp(각각의 결과를 temp 디렉토리에 넣어라!) \;
```



- which
- whereis
- locate
- firewall -config : 포트를 막는다?



## IV. JAVA

- JAVA JDK 8
  - Linux x64 : tar.gz 로 다운로드 하면 디렉토리 운용이 가능하다.

```shell
tar -xvf jdk1.8... : 압축해제
```



- /usr/bin 에서 자바를 심볼릭 링크로 이어준다.

```shell
ln -s /opt/jdk1.8/bin java
```



- .bashrc 에 자바 관련된 것들을 추가한다.
- **아니었다... /etc/profile 에서 해야될 거 같다.**

```shell
JAVA_HOME=/opt/jdk1.8;
PATH=.:$JAVA_HOME/bin:$PATH;
CLASSPATH=$JAVA_HOME/lib;

export PATH JAVA_HOME CLASSPATH
```



## V. RPM  -> YUM(Yellodog updator Modified)

- rpm 불편해서 yum 생김

```shell
rpm -e gedit.3.8.3* : rpm 을 이용해서 gedit 지운거임
rpm -Uvh gedit.3.8.3... : rpm 을 이용해서 gedit 설치
알아서 path 도 등록해주고, 설치도 다 됨ㅎㅎ
```



- 의존성 문제가 발생했다! -> 이 rpm 을 사용하기 위해서는 사전 프로그램이 필요할 수도 있다..



- 그래서 yum 이 나왔다??

- yum.repos.d 폴더 안에 있는 값들로 인터넷에서 다운로드 받아온다!

```shell
yum -y install ...
yum -y localinstall ...
yum remove ...
```



- groupinstall 

```shell
yum groupinstall ... : 패키지와 관련된 것들을 전부 다 설치
```



## VI. 압축, 묶음

- 압축
  - xz, gzip

```shell
xz archive.xz archive
gzip archive.gz archive

xz -d archive.xz
gzip -d archive.gz
```



- 묶음
  - tar

```shell
tar cvfJ atest.tar.xz atest
tar cvfz atest.tar.gz atest

tar xvfJ atest.tar.xz
tar xvfz atest.tar.gz
```



## VII. 방화벽

- firewall-config
- 사용할 포트를 미리 열어놓아야 한다.
  - 런타임, 영구적 모두 설정해놔야 한다.
  - 1521 : tcp



## VIII. Eclipse & Tomcat

- tar 로 압축해제
- /etc 에 옮기고
- /usr/bin 에 링크 설정



- Tomcat : /apache-tomcat/conf/server.xml 에서 포트를 80으로 변경
- Tomcat 실행은 webapps/ROOT 에서 실행됨??



- Tomcat : bin/startup.sh 으로 실행??
  - 둘 중 뭐가 맞는거지..?
  - 밑에 꺼로 실행한다!!