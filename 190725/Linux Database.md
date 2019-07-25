# Linux Database

## I. WorkSpace

- 워크스페이스를 만든다.

- 유저를 만든다.



## II. WAR

- 웹 다이나믹 프로젝트를 export 해서 war 파일을 만든다.
- 서버에 넣는다.
  - apache-tomcat/webapps
  - cp ~/file/*.war .



- tomcat 을 실행시킨다.
- test.war 파일이 풀리면서 test 폴더가 자동으로 생성된다.



- ip 주소를 치면 tomcat sample 페이지가 나오는데,
- 웹 프로젝트를 만들 때, Context root 를 / 로 설정하면 그 페이지가 실행될거다.



## III. Pipe

- ps -ef | grep oracle

- kill -9 [process#]



## IV. Redirect

```shell
ls -al /etc > f1
```



## V. Background

- &



## VI. Service

- systemctl



## VII. MariaDB

- 원래는 MySql 이었다!

```shell
yum -y remove mariadb-libs
yum -y localinstall Maria*

firewall-config : mysql 포트 설정
mysqladmin -u root password '111111'

mysql -u root -p

> USE mysql;

> SELECT user, host FROM user;
> GRANT ALL PRIVILEGES ON *.* TO user1@'70.12.114.%' IDENTIFIED BY '111111';

> SHOW databases;
```

