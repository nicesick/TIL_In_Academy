# Linux_Cron

## I. Cron

- 반복 매크로
  - /etc/crontab : crontab
  - systemctl restart crond

```shell
10 11 24 * * root cp -r /home /backup
```

- hourly, daily, weekly, monthly
  - 매 시간마다 그 폴더 안에 있는 실행파일이 실행됨!

```shell
01 * * * * root run-parts /etc/cron.hourly : run-parts 명령어는 폴더 안의 모든 파일이 실행된다.
```



## II. At

- 일회성 매크로

```shell
at 11:25 am today
at> cp -r /home /backup
at> reboot
at> ctrl + D

at -l : 입력된 매크로 확인
```



## III. NETWORK COMMANDS

```shell
ifup ens33
ifdown ens33

nslookup
> www.naver.com

: 해당 사이트의 IP 값 체크
```

```shell
/etc/resolv.conf : DNS 서버의 IP 값이 들어있다.
```



## IV. Oracle

- swap 을 늘려준다.

```shell
dd if=/dev/zero of=/swapfile bs=1024 count=4194304
mkswap /swapfile
swapon /swapfile
swapon -s

: 이전에 있던 swap 2GB + 이번에 새로만든 swap 4GB 로 총 6GB 가 할당된다.

cd /etc/rc.d/
chmod 755 rc.local
vim rc.local

swapon /swapfile
cat rc.local

reboot

: 다음에 켜도 계속 swap 을 보존되게 한다.
```

- Oracle 설치 및 환경설정

```shell
yum -y localinstall oracle*
service oracle-xe configure

/etc/init.d/oracle-xe start
. /u01/app/oracle/product/11.2.0/xe/bin/oracle_env.sh

/etc/bashrc 에도 등록
```

