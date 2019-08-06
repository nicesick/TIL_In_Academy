# Hadoop 완전분산서버

## I. 하둡 원리

- 네임노드에 요청
- 보조네임노드는... 네임노드 터졌을 때 쓰는거!!... 가 아니다??
  - 중요한 데이터를 백업으로 가지고 있다가 네임노드가 **다시 들어오면** 쏴주는 역할??
- 데이터 노드에 데이터를 쪼개서 저장
- 데이터를 보장하기 위해 저장을 여러 곳에 한다???



## II. 노드 설명

- core-site.xml

```xml
<configuration>
    <property>
        <name>fs.default.name</name>
        <value>hdfs://localhost:9000</value>
# 네임노드 요청을 위한 포트를 설정
    </property>
    <property>
        <name>hadoop.tmp.dir</name>
        <value>/etc/hadoop-1.2.1/tmp</value>
    </property>
</configuration>

```

- hdfs-site.xml

```xml
<configuration>
    <property>
        <name>dfs.replication</name>
        <value>1</value>
    </property>
# 데이터를 여러번 저장하는 횟수
    <property>
        <name>dfs.name.dir</name>
        <value>/etc/hadoop-1.2.1/name</value>
    </property>
# 아.. 못들음..
    <property>
        <name>dfs.data.dir</name>
        <value>/etc/hadoop-1.2.1/data</value>
    </property>
# 데이터가 저장되는 공간
    <property>
        <name>dfs.webhdfs.enabled</name>
        <value>true</value>
    </property>
</configuration>
```

- mapred-site.xml

```xml
<configuration>
    <property>
        <name>mapred.job.tracker</name>
        <value>localhost:9001</value>
    </property>
# 분석을 위한 jobtracker 포트 설정
</configuration>
```



## II. 분산환경 설치

- 서버 4개 준비
  - hadoop1
  - hadoop2
  - hadoop3
  - hadoop4

- ssh 키젠

```shell
# 각자 서버에서 실행해줘야 한다.
ssh-keygen -t dsa -P '' -f ~/.ssh/id_dsa

cd .ssh
cat id_dsa.pub >> authorized_keys
```

- 다른 서버에 키 복사

```shell
scp authorized_keys root@hadoop2:~/.ssh/authorized_keys
```

- hadoop 을 namenode 서버에 이동
- hadoop 설치

- hadoop-env.sh 파일 변경

```shell
export JAVA_HOME=/etc/jdk1.8
export HADOOP_HOME_WARN_SUPPRESS="TRUE"
```

- masters : 보조네임노드 등록

- slaves : 데이터노드 등록

- core-site, hdfs-site, mapred-site 구성

- 각 서버에 배포

- 방화벽 해제 ^오^



## III. 구성

- hadoop1
  - namenode
  - jobtracker
- hadoop2
  - secondary namenode
  - datanode
  - tasktracker
- hadoop3
  - datanode
  - tasktracker
- hadoop4
  - datanode
  - tasktracker

