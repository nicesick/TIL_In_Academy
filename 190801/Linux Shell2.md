# Linux Shell2

## I. Shell Script

- case

```shell
case "$1" in
        start)
                echo "Start Tomcat..."
                startup.sh;;				
# 케이스가 끝날 때에는 꼭 ;; 을 붙여주자!!
        stop)
                echo "Stop Tomcat..."
                shutdown.sh;;
        check)
                echo "Check Tomcat..."

                if [ -n $TOMCAT_HOME ]
                        then
                                echo "Tomcat installed!"
                                ps -ef | grep tomcat
                        else
                                echo "Tomcat not installed..."
                fi;;
                
        yes | y | Y | Yes | YES)
        		;;
esac

```

- 반복문

```shell
count=0

for i in `ls *.sh`
do
	count=` expr $count + 1 `
done

echo $count
```

- while 구문

```shell
while [ 1 ]
do
        echo "Input Command : start , stop , check , q"
        read cmd

        case "$cmd" in
                start)
                        echo "Tomcat Start...";;
                stop)
                        echo "Tomcat Stop...";;
                check)
                        echo "Tomcat Check...";;
                q)
                        echo "Exit..."
                        break;;
                *)
                        echo "??";;

        esac
done

```

- 함수

```shell
startTomcat() {
	echo "Start Tomcat Function..."
	echo $1
# 10
	return
}

stopTomcat() {
	echo "Stop Tomcat Function..."
	echo $1 $2
# 40 50
	return
}
...

start)
	startTomcat 10;;
stop)
	stopTomcat 40 50;;
	
...
```

- set

```shell
$(date)
`date`

# 둘 다 리눅스 명령이 이루어 진다.
# 편한 걸로 하면 되려나??
```



## II. 서버에서 다운받기

- eclipse 에서 test 생성

- web 에 필요한 파일들 업로드

- wget 으로 다운로드

```shell
wget http://70.12.114.54/test/tomcat.tar.gz
```

