# Linux Shell

## I. 하는법

```shell
#!/bin/sh

# 이곳에 쓰고 싶은 명령을 적는다.

exit 0
```

- 실행?

```shell
./name.sh : 만약 x 권한이 있다면,
sh /name.sh : 이걸로도 할 수 있음
```



- 이걸 만들고 나서 /usr/bin 에 옮겨놓으면 명령어처럼 쓸 수 있음



## II. 변수

```shell
v1="string..." : = 은 절대 띄어쓰지 않는다.
echo $v1
echo "$v1"
echo '$v1'	: 변수로 취급 안함
echo \$v1   : 변수로 취급 안함
echo 3+4    : 숫자로 취급 안함
```

```shell
echo "Input numberr...."
read v1
echo $v1
```

```shell
v1=`expr 100 + 111` : 숫자계산은 이렇게!!
echo $v1xz

v2=` expr \( $n1 + 100 \) \* 2 ` : 괄호 조심, 띄어쓰기 조심!! (서로 띄어쓰기를 해야 인식된다...ㅡㅡ)
```



##  III. 파라미터

```shell
sh para1.sh 10 20 30

#!/bin/sh

echo $0 : para1.sh
echo $1 : 10
echo $2 : 20
echo $3 : 30

exit 0
```



## IV. 묘미

```shell
#!/bin/sh

tar cvfJ "$1.tar.xz" $1
mv "$1.tar.xz" $2

find $1 -name "*.$2" -exec cp {} $3 \;
# 우리가 bash 에서 하고 있는 모든 것들을 여기서 한번에 처리가능!!

exit 0
```



## V. if - else

```shell
#!/bin/sh

temp=$1

if [ $temp == "ok" ]
	then
		echo "OK"
	else
		echo "NO"
fi

exit 0
```

- 비교할 때 좀 특이하다..

```shell
if [ -n $temp ]
if [ -z $temp ]
if [ $temp1 -eq $temp2 ]
if [ $temp1 -ne $temp2 ]
if [ $temp1 -gt $temp2 ]
if [ $temp1 -ge $temp2 ]
if [ $temp1 -lt $temp2 ]
if [ $temp1 -le $temp2 ]

if [ -d $fileName ]
if [ -e $fileName ]
if [ -f $fileName ]
if [ -g $fileName ]
if [ -r $fileName ]
if [ -s $fileName ]
if [ -u $fileName ]
if [ -w $fileName ]
if [ -x $fileName ]
```

