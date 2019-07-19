# Linux Server 구축

## I. 세팅

- 소프트웨어 업데이트 해제
  - 소프트웨어 최신 패키지 해제
  - 소프트웨어 공급원 : 업데이트 확인 : 하지않기

  

  - cd /etc/yum.repos.d/
  - gedit CentOS-Base.repo
  - gedit CentOS-Source.repo
  - 업데이트 관련된 부분 지우기
  - mv CentOS-Base.repo CentOS-Base.repo.bak
  - wget http://download.hanbit.co.kr/centos/7/CentOS-Bse.repo
  - rm -f *.repo~
  - yum clean all



- 네트워크 세팅
  - cd /etc/sysconfig/network-scripts/
  - gedit ifcfg-xxxx
  - 자동 IP 할당 해제
  - BOOTPROTO=none
  - IPADDR=192.168.111.100
  - NETMASK=255.255.255.0
  - GATEWAY=192.168.111.2
  - DNS1=192.168.111.2
  - 네트워크 재시작
  - systemctl restart network
  - ifconfig



- SELinux 기능 해제
  - gedit /etc/sysconfig/selinux
  - SELINUX=disabled



- 192.168.111.100 server1 등록
  - gedit /etc/hosts
  - 192.168.111.100 server1
  - ping server1



## II. Server2 구축

- Network Adapter Advanced.. : MAC Address Generate
- 주소값 복사

- gedit /etc/sysconfig/network-scripts/ifcfg-ens33
- mac address 값 붙여넣기