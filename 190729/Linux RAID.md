# Linux RAID

## I. RAID

- 하드를 없앨 때는 항상 fatab 에서 지우고 빼야 한다.

- 만약 4TB 가 1TB 2개, 2TB 1개로 나뉘어져 있다면,

  2.3TB 짜리는 들어올 수 없다.

- 이럴 경우 디스크를 하나로 합치는게, RAID 기술이다.

- 이 기술을 이용해, 빅데이터 환경을 구축했다.

  - 하드웨어 RAID : 개비쌈
  - 소프트웨어 RAID : 저렴



## II. vim Command & bash Command

- u : undo
- df : 마운트 된 것들 조회



## III. RAID 종류

- Linear RAID : 한 쪽이 다 차면 다음 하드로 넘어감

  

- RAID0 : 한 파일이 양 쪽으로 나눠져서 들어갈 수 있음

  하드 두 개가 동시에 돌아가므로 시간이 절약됨

  

- RAID1 : 하드 두 개가 미러링으로 들어감

  한 하드가 망가져도 다른 쪽에서 찾아와 복구할 수 있음



- RAID5 : RAID0 처럼 여러 하드에 접근하여 시간이 절약됨

  만약 하드가 1개? 망가져도 패리티 정보를 이용해 복구 가능
  
  - 아하.. 하드 하나를 패리티 공간으로 사용함



- RAID6 : RAID5 개선, 따라서 하드가 2개? 망가져도 복구 가능



## IV. RAID 사용

- RAID 전용 System 으로 파티션 할당

```shell
fdisk /dev/sdb
Command : n
Select : p
Partition number : 1
First sector :
Last sector :
Command : t
Hex Code : fd (Linux raid autodetect)
Command : p
Command : w
```



- mdadm 명령어를 이용해 두 하드를 한번에 포맷한다.

```shell
mdadm --create /dev/md9 --level=linear --raid-devices=2 /dev/sdb1 /dev/sdc1

mkfs -t ext4 /dev/md9
```



- mount 한다.

```shell
mkdir linearRAID
mount /dev/md9 linearRAID
```



- 없앨 때 : 원상복귀

```shell
mdadm --stop /dev/md9

fdisk /dev/sdb
Command : d
Command : w
```



- 결함 복구 : linearRAID, RAID0 (복구 불가)

```shell
mdadm --create /dev/md9 --level=linear --raid-devices=2 /dev/sdb1 /dev/sdc1
```



- 결함 복구 : RAID1, RAID5 (복구 가능) (혹은 증설할 때에는 사용)

```shell
mdadm /dev/md1 --add /dev/sdg1
```

