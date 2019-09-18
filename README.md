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

​	Hadoop

​	R

​	Android



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