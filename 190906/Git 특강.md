# Git 특강

## I. Branching

- 처음 시작할 땐

  - git init

  

  - git diff : 코드가 뭐가 바꼈는지 확인
  - git merge [합치고 싶은 브랜치 이름] : 브랜치 머지

  

- 브랜치는 가지치기

- master 를 기준으로 브랜치를 나눌 수 있다

- 나중에는 master 에 다시 합쳐질 수도 있다

- 왜 branch를 할까?

  - 요즘 기업에서는 git 에 push 가 되면 본 서버로 바로 코드가 업로드가 되게 스크립트를 짜놨다. (CI & CD (?))
  - 만약 누군가 push 를 하면 (만약 잘못된 코드라 하더라도) 바로 서버에 적용된다는 뜻이다.
  - 그래서 브랜치를 만들어서 master 와는 다른 세계?? 를 새로 만드는 거다.
  - 나중에 해당 브랜치를 다 테스트 하고 나서 확신이 들면 master 에 합쳐진다.



- 모든 명령어는 git branch 로 시작



- git branch : branch 의 상태, 현재 어느 branch 에 속해있는 지 확인

- git branch [브랜치 이름] : 해당 이름으로 브랜치를 만든다.

- git checkout 혹은 git switch [브랜치 이름] : 해당 브랜치로 이동



- branch 를 만들고 나면 master 와 test 는 같이 있다가
- test 를 따로 commit 하게 되면 따로 가지가 뻗혀 나간다.



- 여기서 연습해 볼수 있다.
  - http://git-school.github.io/visualizing-git/

- 근데 사실 브랜치는 HEAD 포인터가 움직이는 것 뿐이다?

- 합칠 때에는 git merge [합치고 싶은 브랜치 이름] 으로 실행
- 합쳐진 브랜치는 앞으로 절대 다시 사용하지 않는다.
  - 합쳐진 브랜치는 지운다.
  - git branch -d test : test 브랜치를 지운다.



- git restore : 마지막 커밋 상황으로 돌려놓는다.

- git reset --hard HEAD : 모든 파일을 마지막 커밋 상황으로 돌려놓는다.



- 만약 새로 생긴 파일들을 없애고 싶다면
  - add 가 안되어 있을 때,
    - 걍 지운다.
  - add 가 되어 있을 때,
    - git rm index.html



- 이제 모든 작업은 브랜치에서 한다.
  - new 브랜치를 만든 뒤,
  - index.html 을 다시 해보자



- VSCode 를 쓰자
  - ! tab 을 누르면 기본적인 구조가 생성된다. ㄷㄷ
  - ol>li*3 을 하면 자동으로 태그가 생성되기도 한다.
  - 페이지를 확인할 수 있는 방법 : VSCode > reveal in browser > index.html



- 머지의 종류
  - fast-forward merge : branch 의 파일들이 섞일 필요 없이 추가만 되도 될 때
    - master 에서 git merge new
  - auto merge : 수정이 되었지만 같은 파일을 건들지 않았을 때
    - merge commit 이 일어나면서 브랜치가 합쳐진다.
  - merge with conflict : 같은 파일이 동시에 수정되었을 때
    - Accept CurrentChange : master 의 값이 진실
    - Accept Incoming Change : conflict 의 값이 진실
    - Accept Both Change : 둘 다 합친다.



- 브랜치를 만들면서 이동하려면?
  - git checkout -b neo
  - git switch -c neo



- Conflict 가 났을 때 무슨 정보가 변경되었는지 history 에 꼭 남기도록 하자!



## II. 팀원들과 push, pull 할 때(master, slave) -> Branch & Pull Request 패턴

- 프로젝트 생성 (master)

  - branch-pr : index.html, README.md
  - git init
  - git add .
  - git commit
  - Github 에서 새 레포지토리 만들기
  - 원격 저장소 추가
    - git remote add origin [주소]
  - git push origin master
    - git push [원격저장소 주소] [브랜치 이름]
  - git branch leader

- 프로젝트 clone (slave)

  - git clone [저장소 주소]
  - git branch slave

- 각자 commit
  - git push origin slave (단, 팀장이 권한허가를 해줘야함)
    - master branch 에 넣고 싶다면?
    - New pull request
      - 리더에게 요청 (Reviwer 에 있는 분께 부탁)

  

  - git push origin leader
    - Settings > Collaborators > 팀원 추가
    - Settings > Branch > Rules
      - Require pull request reviews before merging : 머지 전 반드시 owner에게 검토를 받아야 한다.
      - Branch name pattern 을 통해 해당 브랜치에 적용할 수 있다.



## IV. 깃헙 페이지스

- Web 프로젝트를 올리고, Settings > Github Pages 를 생성
- 해당 주소로 하면 바로 실행됨