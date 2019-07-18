# Linux

## I. 가상머신

- VMware Player



## II. 설치

- 설치하고, '가상의 HW' 를 설치
  - I will install the operating system later :  그래서 OS 는 나중에 설치해도 되는 거임
  - CentOS 64-bit - C:\CENTOS : 해당 폴더에 컴퓨터를 설치하겠다.



## III. Setting

- Memory 2GB 로 변경

- CD/DVD 에서 User ISO image file 선택
- CentOS 이미지 선택



## IV. CentOS 설치

- 언어 : 한국어
- 키보드 : 한국어 + 영어

- 소프트웨어선택 : 개발 및 창조를 위한 워크스테이션

- 네트워크 및 호스트 이름 : 킴

- 설치 목적지 : 파티션을 설정합니다. (ex > C 로만 쓸거냐, D도 만들거냐)

  - 표준 파티션

  - +버튼을 눌러서 'swap 2GB' 추가

    - swap 이란 : 하드디스크의 가상메모리 공간
    - 느리지만 메모리처럼 쓸 수 있다.

    

  - +버튼을 눌러서 '/ 나머지 용량' 추가

- ROOT 암호 설정 : 111111

- 사용자생성 : 

  - 성명 - centos
  - 암호 - 111111