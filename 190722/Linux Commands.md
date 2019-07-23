# Linux Commands

## I. basic

- pwd : print working directory
- ls
- ls -l
- cd : change directory
- cd - : 이전 폴더?
- cd ~centos : centos 유저의 홈으로 가기
- whoami
- su : switch user
- su - [사용자이름]
- exit

- history

- cat

- mkdir
- mkdir -p : 하위 디텍토리까지 만들 수 있음
- touch

- rm

- rm -i : 확인 후 삭제

- rm -rf : 디텍토리 삭제, 삭제 중 확인 안함

- head

- tail

- more, less

- clear : ctrl + l 써도 됨

  

## II. Vim

- 명령 모드 : esc
- 입력 모드 : i 또는 a 또는 A 또는 I 또는 o 또는 O 또는 s 또는 S 또는 cw
- ex 모드



- 저장 후 종료 : wq [file_name]
- 복사, 붙여넣기 : yy p
- 강제로 나가기 : !

- swp 파일 : 엔터치면 계속 진행된다.
- 커서를 맨 앞, 맨 뒤로 옮기기 : ^, $

- 커서를 맨 위, 맨 뒤로 옮기기 : gg, G

- 에디터 줄 수 보이기 : set nu

- 해당 줄로 이동 : :30

- 라인 지우기 : dd
- 문자열 찾기 : /문자열, 다음 라인 : n
- 문자열 바꾸기 : %s/문자열/바꿀문자열



## III. Mount

- /dev/sr0(물리적인 CD 라고 생각하면 됨) : /run/media 밑에 마운트 되어 있다??

- mount : 현재 마운트된 것들 출력
- umount : 현재 마운트된 것 해제
- umount cdrom : 현재 cdrom 에 들어있는 것들 마운트 해제



- 내가 원하는 폴더에 마운트 하려면?
  - mkdir mycdrom
  - mount /dev/cdrom /mycdrom : 그럼 cdrom 을 마운트 한거다!!
  - umount /mycdrom : 연결된 마운트를 해제!



## IV. User

- 리눅스 유저는 로그인하는 거랑 같다.
- 네이버에서 로그인하면 리눅스의 유저에 접근하는 거임ㅎㅎ



- adduser user1
- passwd user1

- userdel -r : 유저와 홈 디렉토리까지 삭제
- chage : 비밀번호 관련 설정



## V. Group

- groups [유저 이름] : 유저가 어느 그룹에 있는지 체크
- groupadd
- groupmod
- groupdel
- gpasswd



##  VI. Setting

- /etc/skel : 이 파일을 세팅하면 새로운 유저를 만들 때 default 로 만들어진다.

- .bashrc : bash 를 켰을 때의 설정을 할 수 있다.
- 설정하고 다시 적용하려면 . .bashrc 를 해주면 된다.
- chmod 664 [파일 이름] : 파일의 권한 수정