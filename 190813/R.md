# R

## I. 시작

- 통계를 기반으로 한 데이터 분석 오픈소스
- 하둡 에코 시스템처럼 다양한 패키지들이 준비되어 있다.
- R - JAVA, R - HIVE 연동 가능



## II. 설치

https://ftp.harukasan.org/CRAN/

- 여기서 설치



## III. 한계

- 이거 하나가지고 쓰긴 뭐함
- IDE 쓴다
  - R Studio 를 깐다.

https://www.rstudio.com/products/rstudio/download/#download



## IV. 사용

```R
> a <- 100 
# 변수 지정
```

- 프로젝트 생성

  - File -> New Project -> New Directory

  

  - Directory name : r1
  - Create project as subdirectory of : C:/Rstudio



- RScript 생성
  - 초록색 플러스 -> RScript

```R
a <- 100;
b <- 200;
c <- a + b;
print(c)
# 마지막 줄은 ; 를 붙이지 않는다.
```

- Source 를 눌러서 실행

- Run 은 드래그하고 해당 지역만 실행됨



- Environment 해제
  - 옆에 빗자루 쓰면 됨



- 패키지 다운

```R
install.packages("randomForest")
# "" 있어야 함
# AppData/Local/Temp/RtmpUtU0Gp/downloaded_packages 에 압축파일이 다운됨
# 이 파일은 C:\Program Files\R\R-3.6.1\library 에 저장됨
# 잘 안되면 여기 지우면 됨

library(randomForest)
# "" 없어야 함?
# 파일의 맨 위에 추가하면 됨
```



- 신기한 거 (벡터)

```R
a <- c(1:10);
a <- c(1,3 , c(4:6));
# 이러면 배열 들어감 ^오^
# R 에서는 Vector 라고 한다.
# 벡터 안에 벡터도 쌉가능

names(a) <- c("lee","kim","han");
# 벡터에 이름을 지정할 수도 있다는거??

print(sum(a));
# 근데 이렇게 해도 숫자만 계산을 될 수 있다는거??

b <- mean(a);
c <- NA;
# Not Available

is.na();
# 이 함수로 NA 값들을 제외할 수 있다.

c <- NULL;
# NA : 있어야 하는데 값이 없을 때,
# NULL : 없어야 할거에 값이 없을 때?

is.null();

sex <- factor("m", c("m","f"));
# c 를 통해 들어갈 수 있는 값들을 제한한다.

levels(sex)[1]
# 범주 값들을 출력할 수 있다.
# 단 인덱스가 1부터 시작됨
```



- 더 신기한거

```R
v1 <- c(1:10);
print(v1[-1])
# 이러면 첫번 째 인자만 빼고 실행됨

print(v1[-1:-3])
# 이러면 첫번 째 부터 세번 째까지 빼고 실행됨

print(v1["lee"])
# 만약 이름이 지정되어 있다면 이렇게 접근도 가능하다.

print(v1[c("lee","kim")]);
# 만약 이렇게 접근하려면 c 를 꼭 써서 벡터로 만들어 줘야 한다.

print(v1[5:8])
# 이렇게 쉽게 접근 가능하다.

length(v1);
# 벡터의 길이

NROW(v1);
# 벡터의 길이 : 아마도 2차원에서 접근할 때 쓰이는 거 같음

identical();
%in%

x <- c(1,2,3,4,5);

x + 1
x - 10
# 각각의 값들에게 +1, -10 실행

union();
# 합집합
intersect();
# 교집합
setdiff();
# 차집합

setequal();
# 집합 비교

a <- c(1:10)
b <- seq(1, 10, 2)
# seq 를 이용하면 2씩 증가하는 것도 가능하다!

c <- rep(1, times=5)
# 1 을 다섯번 반복해서 만든다.

c <- rep(1:2, times=2, each=5)
# 1 1 1 1 1 2 2 2 2 2 1 1 1 1 1 2 2 2 2 2
```



- 신기한 거 (리스트)

```R
# 여기서 리스트는 Key, Value 형식을 의미
a <- list(name=c("a", "b", "c", "d"), score=c(100, 90 , 90, 70))

print(a)
# 결과
# $name
# "a" "b" "c" "d"
# $score
# 100  90  90  70

print(a[1])
# 결과
# $name
# "a" "b" "c" "d"

print(a$name)
# 결과
# "a" "b" "c" "d"

a <- list(s1=c(80, 90, 70, 88), s2=c(90, 100, 80, 30))

print(a)
print(mean(a$s1))
print(mean(a$s2))

print(mean(c(mean(a$s1), mean(a$s2))))
# 무조건 2개 이상이면 c 를 써야된다는 것!
# 꼭 기억하자!!

print(mean(c(a$s1[1],a$s2[4])))
# s1 의 첫번째 인자와 s2 의 네번째 인자의 평균

print(mean(a$s1[c(1,4)]))
# s1 의 첫번째 인자와 네번째 인자의 평균
```



- 신기한 거 (행렬)

```R
data <- c(1:9)
mat <- matrix(data, nrow=3)

print(mat)
# 결과
#      [,1] [,2] [,3]
# [1,]    1    4    7
# [2,]    2    5    8
# [3,]    3    6    9

mat <- matrix(data, nrow=3, byrow=TRUE)
# 결과
#      [,1] [,2] [,3]
# [1,]    1    2    3
# [2,]    4    5    6
# [3,]    7    8    9

rownames(mat) <- c("r1","r2","r3")
colnames(mat) <- c("c1","c2","c3")

print(mat["r2","c3"])
# 이렇게 값에 접근가능

print(mat[c("r2","r3"),c("c2","c3")])
# 이렇게 한번에 접근도 쌉가능

print(mean(mat[2,]))
print(mean(mat[,2]))
# 이렇게 한 줄에 접근 가능

A + x
A + B
A %*% B
```



- 데이터 프레임

```R
# 다양한 타입이 들어갈 수 있다.
df <- data.frame(x=c(1:5), y=c(5:9), z=c(10:14))

print(df$x)
print(df[2,])
print(df[,2])
print(df[c(2:5),c(2:3)])

str(df)
# df 의 현재 상태를 볼 수 있다.

df2 <- data.frame(
  df1$a,
  df1$b,
  df1$c
)
# 처음에 이렇게 생각했는데

df2 <- df1[,-4]
# 이렇게 하는 게 더 편해보여

df2 <- df1[c(-2,-3)]
# 이것도 봐보자

print(df2)
print(as.numeric(df2[1,]))
# 누메릭으로 바꾸면 평균도 계산 가능!!

print(class(df3))
# 클래스로 무슨 형태인지 알 수 있다.
# 연산을 할 때에는 numeric 을 쓰는 것을 생각하자!
```



- 딴거

```R
d1 <- c(70,80,98,50,NA)
print(sum(d1,na.rm = TRUE))
```



- 함수

```R
f1 <- function(...) {
# 이러면 여러개 넣는 거 가능
  return (100)
}

result <- f1()
print(result)

n <- 100

f1 <- function() {
    n <<- 1
    # 전역변수를 바꾼다.
    
    n <- 1
    # 지역변수 n 을 만든다.
    # 전역변수보다 우선순위가 높다.
}

f1 <- function(df) {
    df$a <- c(4,5,6)
}

df <- data.frame(a=c(1,2,3))
f1(df)
df

# 함수는 call by value 값이다!!
```

- 변수 제거

```R
rm(list=ls())
print(list)
```



- R을 쓰는 이유?
  - 통계 패키지가 강력하다??
  - HIVE 에서 불필요한 계산을 줄이고 여기서 계산하자?