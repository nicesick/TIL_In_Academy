# Linux HDD

## I. IDE? -> SATA?

- IDE 방식은 4개 달 수 있다.

- 개인용 컴터는 SATA 방식으로 바꼈다고 한다.



## II. SCSI (스카시 방식?) -> SA-SCSI (SAS?)

- 서버에서 자주 쓰이는 방식?
- 0 ~ 15 까지 16개 쓸 수 있다.



- 서버용 컴퓨터는 SA-SCSI 방식으로 바꼈다고 한다.
- cdrom 때문에 IDE 방식이 같이 있긴 하다.



## III. 하드 추가

- Edit VM 을 열어서 Add 를 통해 하드디스크를 하나 더 추가한다.

- fdisk 를 이용해서 파티션을 나눈다.
- mkfs.ext4 를 이용해서 포맷을 한다.
- /etc/fstab 에서 항상 마운트 되도록 한다.

```shell
fdisk /dev/sdb
Command : n
Select : p
Partition number : 1
First sector : 
Last sector :
Command : p
Command : w
```

```shell
mkfs.ext4 /dev/sdb1

vim /etc/fstab
```

```shell
/dev/sdb1				/root/data1				ext4		defaults		1 2
```

