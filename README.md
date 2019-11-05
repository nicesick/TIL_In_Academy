# TIL For Academy



## Introduction

This Repository is for what I learn in academy (2019.05.10 ~ 2019.12.13)



## Contents

1. [Git](#git)

2.  [Java](#java)

- JDK 1.8

3. [SQL](#sql)

- Oracle : 11g Express Edition

4. [Web Programming](#web)

- JQuery : /1.12.4/jquery.min.js

- JSON : json-simple-1.1.1.jar

- JSTL
  - jstl
  - standard
  - cos

5. [Spring Framework](#spring)

- Oracle : oracle6_g

- Maven
  - Spring : 4.2.5.RELEASE
  - aspectj : 1.6.5
  - log4j : 1.2.15

  - mybatis : 3.2.3
  - mybatis-spring : 1.2.2

6. [Mini Team Project](https://github.com/nicesick/MultiCampus_Web_MiniProject_Team_3)

7.  [Linux](#linux)

- CentOS : centos7

- Tomcat : apache-tomcat-9.0.22.tar.gz

- Eclipse : eclipse-jee-oxygen-3a-linux-gtk-x86_64.tar.gz

- Oracle : oracle-xe-11.2.0-1.0.x86_64.rpm

- Maria : MariaDB-10.0.15-centos7_0-x86_64*.rpm
  
  - MariaDB-client
  - MariaDB-server
  - MariaDB-common

8. [Hadoop](#hadoop)

- Hadoop : hadoop-1.2.1.tar.gz
- hive : hive-1.0.1.tar.gz

9. [R](#r)

10. [Android](#android)

- Android Studio

11. [Network](#network)

12. [CAN](#can)

- Driver : RealSYS_USB_Device_Driver(20170316)
- CAN : CANPro_v1.4

13. [CAN Communication Mini Project](https://github.com/nicesick/SimpleCanComm)

14. [ConnectedCarControlSystem Final Project](https://github.com/nicesick/GongJo)

15. [AWS](#aws)



## Git

- [190521](/190521)
  - Git
  - Markdown editor : Typora
- [190522](/190522)
  - Simple Git init & push
- [190906](/190906)
  - Git branch & merge
    - fast-forward merge
    - auto merge (without conflict)
    - merge with conflict
  - Git pull request

[To Contents](#contents)



## Java

- [190522](/190522)
  - Eclipse short-cut : Generate Constructor & Getter and Setter
- [190523](/190523)
  - Throw Exception
  - Static(or Class) variables
- [190528](/190528)
  - Inheritance
  - specialize using 'instanceof'

[To Contents](#contents)



## SQL

- [190530](/190530)
  - SQL
  - JDBC in Java
- [190603](/190603)
  - Functions
  - View
  - SubQuery
  - Join
- [190610](/190610)
  - Commit, Rollback
  - Small Team Project
    - CRUD implementation
    - Communication with Java and Oracle
    - Abstraction using Biz, Dao Class

[To Contents](#contents)



## Web

### HTML

- [190611](/190611)
  - Introduction : Client Side Languages & Server Side Languages
  - JavaEE & Tomcat Installation
  - HTML
    - Element
    - Tags
- [190612](/190612)
  - HTML Tags
    - a
    - table
    - **form**
    - audio
    - video
    - fieldset



### CSS

- [190613](/190613)
  - ID, Classifier(#, .)
  - CSS Tags
    - Google Fonts
    - Text
    - Block Type (margin, padding)
    - Background
  - Skill
    - How to make row pattern in list
      - display : inline;
- [190614](/190614)
  - Float
  - Bootstrap
    - Download Template



### JavaScript

- [190618](/190618)
  - JavaScript
    - alert
    - console.log
  - JavaScript Function : Form Argument
    - this.form
  - CodeMix
- [190619](/190619)
  - Object (== JSON)
  - Function Skills
  - Class
  - Error
- [190620](/190620)
  - SetTimeout
  - SetInterval



### JQuery

- [190621](/190621)
  - JQuery : Installation
  - Basic Use
    - val, html, css
    - not, eq
    - event
      - click
      - hover (mouseenter + mouseleave)
      - on
    - form
    - show, hide, toggle, animate
    - addClass, removeClass
    - foreach



### AJAX & JSON

- [190624](/190624)
  - JQuery : scrollTop
  - AJAX : Communication Skill with Server
  - Server Side : Servlet
  - AJAX Request
  - JSON Response



### HighChart

- [190625](/190625)
  
  - HighChart Uses
  - Data Type
    - JSON
    - XML
      - How to parse xml : find function
  - OpenAPI
  
- [190626](/190626)
  - CROSS-CHECK PROBLEM
  - HTML5 New Functions
    - Storage : Session, Local
    - Web Socket
    - Server Event
    - Drag And Drop
    - Geolocation
  - KakaoMapAPI
  
  

### Servlet

- [190627](/190627)
  - Servlet
  - Response Method
    - PrintWriter
    - JSP
  - JSP Library
    - JSTL
      - if
      - choose, when, otherwise
      - foreach
  - Servlet Dispatcher
    - ask?type=''

- [190628](/190628)
  - Component Models
  - JSP & JQuery
  - Double Request : 'sendRedirect' function
  - Servlet Library
    - COS : the library for uploading the files
    - MultipartRequest
  - Hidden : How to send additional datas

- [190701](/190701)
  - Dispatcher
    - web.xml : servlet-mapping
  - partial page change
    - jsp:include
- [190702](/190702)
  - HttpSession
  - SimpleWebProject
    - Dispatcher
    - Servlet
    - Partial Page
      - jsp:include

[To Contents](#contents)



## Spring

- [190702](/190702)
  - Spring Properties
    - IoC
    - AoP
    - POJO
  - Spring Installation
  - Maven
  - Spring Setting
  - Spring.xml
    - AbstractApplicationContext
    - bean
- [190703](/190703)
  - xml import
  - Annotation
    - context
  - Mixing : bean & Annotation
  - AoP
    - aop : config, pointcut, aspect
  - JoinPoint
  - AoP - Annotation
- [190704](/190704)
  - MyBatis & Oracle
    - tx
  - spring.xml setting for mybatis
  - MyBatis Uses
    - Mapper
    - Annotation : Transactional
  - SpringMVC
    - web.xml Setting
    - spring.xml Setting
      - mvc
      - ViewResolver

- [190705](/190705)
  - SpringMVC
    - Argument
    - ClassMaker
  - Multipartfile : How to upload files
    - spring.xml Setting
  - File Upload : FileOutputStream

[To Contents](#contents)



## Linux

- [190718](/190718)
  - VMware Player
  - How to install CentOS
- [190719](/190719)
  - Linux Setting
    - Remove Update (Stay this version) : yum repo setting
    - Network : Static Network Address Setting
    - Host : IP & host mapping

- [190722](/190722)

  - Linux Commands :Basic
  - Vim Editor
  - Mount
  - Administrator
    - User Command
    - Group Command
    - Skel & .bashrc

- [190723](/190723)

  - How to install Programs
  - Hard & Symbolic Link
  - Linux Command
    - find
    - whichis
    - where
  - program installation
    - Java : jdk1.8
    - Apache Tomcat
    - rpm & yum
  - Compression Or DeCompression
    - tar
    - xz , gzip ...
  - How to firewall config

- [190724](/190724)

  - Marco
    - Cron
    - At
  - How to install OracleDB

- [190725](/190725)

  - Creating WorkSpace Using GUI
  - How to start server : WAR
  - How to install MariaDB

- [190726](/190726)

  - HDD System
    - IDE > SATA
    - SCSI > SA-SCSI

  - HDD control Command
    - fdisk
    - fstab

- [190729](/190729)

  - RAID : if the situation the HDD is lost
    - Linear RAID
    - RAID0
    - RAID1
    - RAID5
    - RAID6
  - RAID Command
    - mdadm
    - mkfs

- [190730](/190730)

  - LVM
    - pvcreate
    - vgcreate
    - lvcreate
  - Installating CentOS with RAID1

- [190731](/190731) & [190801](/190801)

  - Shell Programming
    - variables
    - argument
    - programming with bash commands
    - if - else
    - case
    - for & while
    - function

- [190802](/190802)
  - ROOT : How to start '/' in Tomcat Server
    - Windows
    - Linux
  - Log4j
  - Redirect : 404 Error Page

[To Contents](#contents)



## Hadoop

- [190805](/190805)
  - Introduction : Big Data
    - Before Big Data...
    - Hadoop
  - Installation for Hadoop in Linux
    - Type
      - Single
      - Virtual Distribution
      - Fully Distribution
  - Virtual Distribution
    - Settings

- [190806](/190806)
  - Hadoop Fully Distribution
    - Settings
- [190807](/190807)
  - File Systems
    - Before Hadoop
      - DAS
      - NAS
      - SAN
    - Hadoop : HDFS + Map Reduce + Distributed System
  - Hive : Hadoop Eco System
    - Installation MySql
    - Settings
    - Uses

- [190808](/190808)
  - Hive SQL
    - How to Use SQL
    - How to load Datas
  - Java + Hive
    - Hive Listener
    - Librarys
    - Code

[To Contents](#contents)



## R

- [190813](/190813)
- [190816](/190816)
- [190819](/190819)
- [190820](/190820)
- [190821](/190821)

[To Contents](#contents)



## Android

- [190826](/190826)
  - Why we make Native App?
    - Security
    - Push Notification
  - Android Studio Installation

- [190827](/190827)
  - AVD Manager : How to make Virtual Phone
  - How to make New Project
    - minSdkVersion
    - targetSdkVersion
    - dependencies : External Libraries
  - Basic Structure
    - Java
      - MainActivity
    - Res
      - layout
      - mipmap
      - values
      - drawable
    - AndroidManifest
  - Build
    - Generate Signed Bundle : Distributed Apk
    - Build Bundle : Debug Apk
  - Logcat
  - LifeCycle
    - OnCreate
    - OnResume
    - OnPause
    - OnDestroy
  - Widget
    - Layout
    - View
    - ViewGroup
    - How to get Widget

- [190828](/190828)

  - Button
  - Text
    - TextView
    - EditText

  - EventListener
  - OnClickListener
    - OnCheckedChangeListener
    - OnTouchListener
      - GestureDetector
    - OnKeyDown
    - OnConfigurationChanged
  
  - How to save datas
      - Bundle
          - OnSaveInstanceState
      - SharedPreferences
  - Custom View
    - Toast
    - Dialog
    - ProgressBar
    - progressDialog

- [190829](/190829)

  - Android Contents
    - Activity
    - Service
    - BroadCast Receiver
    - Content Provider
  - How to move Activitys
    - Intent
    - startActivityForResult
    - OnActivityResult
    - setResult
  - How to execute External App
  - Fragment
    - getSupportFragmentManager
    - beginTransaction
    - replace
    - commit

- [190830](/190830)

  - MenuBar
    - OnCreateOptionsMenu
    - OnOptionsItemSelected
  - CalendarView
  - TimePicker

  - Service
  - startService
    - onStartCommand > Runnable > Thread.start
    - How to send the data to activity
      - onNewIntent
      - Flag Setting
        - FLAG_ACTIVITY_NEW_TASK
        - FLAG_ACTIVITY_SINGLE_TOP
        - FLAG_ACTIVITY_CLEAR_TOP
      - startAcvitity
    - Binder : How to connect the service in activity
      - ServiceConnection
      - IBinder
      - bindService
  
  - Broadcast Receiver
      - AndroidManifest Setting
      - permission
      - receiver

- [190902](/190902)
  - Broadcast Receiver
    - IntentFilter
    - BroadcastReceiver
    - registerReceiver
  - listView
    - BaseAdapter
    - setAdapter
    - addItem
  - listViewEvent
    - OnItemClick
  - Spinner

- [190903](/190903)
  - WebView
    - android > web
    - web > android
  - seekBar
  - Thread
    - Handler
      - obtainMessage
      - sendMessage
    - runOnUiThread
    - handler.post
    - handler.postDelayed

- [190904](/190904)
  - AsyncTask
    - onPreExecute
    - onProgressUpdate
    - onPostExecute
    - doInBackground
    - executeOnExecutor
  - Network
    - URLConnection
    - InputStream
    - StringBuilder
  - JSON
    - JSONArray
    - JSONObject

- [190910](/190910)
  - SQLite
    - SQLiteOpenHelper
    - SQLiteDatabase
    - execSQL
    - rawQuery
    - Cursor
  - Content Provider
    - setAction
    - startActivityForResult
    - OnActivityResult
    - Uri
    - ContentResolver

- [190911](/190911)
  - MediaPlayer
  - VideoView
  - YoutubeAPI
  - Location
    - LocationManager
    - getSystemService
      - LOCATION_SERVICE
    - LocationListener
    - requestLocationUpdates
  - Map
    - SupportMapFragment
    - GoogleMap

- [190916](/190916)
  - Vibrator
  - Ringtone

[To Contents](#contents)



## Network

- [190917](/190917)
  - Thread
  - ThreadGroup

- [190918](/190918)
  - ThreadContol
    - sleep
    - interrupt
      - InterruptedException
  - ThreadInfo
    - Thread.currentThread
  - Banned (can't use anymore) > use flag
    - suspend
    - resume
    - stop
  - synchronized
    - wait
    - notify

- [190919](/190919)
  - PipedReader, PipedWriter
  - InputStream
    - BufferedInputStream
    - InputStreamReader
    - FileInputStream
    - DataInputStream
    - ObjectInputStream
  - OutputStream
    - BufferedOutputStream
    - OutputStreamWriter
    - FileOutputStream
    - DataOutputStream
    - ObjectOutputStream
      - Serializable
      - transient

- [190923](/190923)
  - Socket
    - Server
      - ServerSocket (+ port)
      - Socket
      - OutputStream
      - DataOutputStream
    - Client
      - Socket (+ ip, port)
      - InputStream
      - DataInputStream
  - Map<ip, socket>
    - Collection
      - keys
      - values

[To Contents](#contents)



## CAN

- [190926](/190926)
  - How to install CAN
  - JAVA + CAN
    - RXTXcomm.jar
    - rxtxParallel.dll
    - rxtxSerial.dll
  - CAN Protocol

[To Contents](#contents)



## AWS

- [191105](/191105)
  - IAM
    - 유저생성
    - 그룹생성
    - 그룹-유저 추가
  - MFA
    - MFA 활성화
    - Authy
  - 실습
    - VPC
      - VPC생성
      - Subnet 생성
      - Gateway 생성
      - 라우팅 테이블 생성
      - 보안 그룹 생성
      - 인바운드, 아웃바운드 규칙 생성
    - EC2
      - 인스턴스 생성
      - 인스턴스 이미지 생성
      - 로드 밸런서 생성
        - 로드 밸런서 보안 그룹 선택
        - 로드 밸런서 라우팅 구성

[To Contents](#contents)