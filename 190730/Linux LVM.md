# Linux LVM

## I. LVM

- 하드를 볼륨개념으로 나눠서 유기적으로 크기를 할당할 수 있다.



## II. LVM 설치

- 디스크 파티션 할당

```shell
fdisk /dev/sdb
Command : n
Select : p
Partition number : 1
First sector :
Last sector :
Command : t
Hex Code : 8e
Command : p
Command : w
```

- LVM 설정

```shell
pvcreate /dev/sdb1
pvcreate /dev/sdc1

vgcreate myVG /dev/sdb1 /dev/sdc1

vgdisplay

lvcreate --size 1G --name myLG1 myVG
lvcreate --size 3G --name myLG2 myVG
lvcreate --extents 100%FREE --name myLG3 myVG
```

- LVM 포맷

```shell
mkfs -t ext4 /dev/myVG/myLG1
mkfs -t ext4 /dev/myVG/myLG2
mkfs -t ext4 /dev/myVG/myLG3
```

- mount

```shell
mount /dev/myVG/myLG1 myLG1
mount /dev/myVG/myLG2 myLG2
mount /dev/myVG/myLG3 myLG3
```



## III. RAID1 에 CENTOS 설치

- 새로운 가상머신을 만들고, 하드 30GB 추가

- CD-ROM 에 CentOS 삽입
- 실행
  - 디스크에서 둘 다 클릭 후, swap 과 / 추가
  - 단, 장치 유형을 RAID RAID 레벨을 1로 지정
  - 설치시작



- 다되고 나서 만약 미러 하나가 망가진다면
  - 다시 디스크 넣고

```shell
fdisk -l /dev/sda : swap 과 root 사이즈 확인

fdisk /dev/sdb
First 와 Last를 위의 정보를 토대로 입력

mdadm /dev/md/swap --add /dev/sdb1
mdadm /dev/md/root --add /dev/sdb2
```



## IV. Linux 구축

- 몰랐던 점

```shell
1. git 안에서..
scp 명령어로 하면 파일이 바로 보내진다.

2. 방화벽
firewall-cmd --permanent --add-services=http
firewall-cmd --permanent --add-port=1521
```

