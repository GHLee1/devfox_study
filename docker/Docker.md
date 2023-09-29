# Docker

### docker란

컨테이너형 애플리케이션의 빌드,배포 및 관리를 위한 오픈 소스 플랫폼

### Docker쓰는이유

-개발자가 만든 그대로 어디서든 돌아감(containerだから) 확장/축소가 쉽고 MSA Devops에 적합함

### 도커를 쓰는 방법

1. VirtualBox에 네트워크 설정을 한뒤 리눅스설치 후 그위에 도커를 올림
2. window에서 docker desktop을 설치해서 쓰는 방법

### 도커 구성

**1.  이미지** : 자바로 치면 class, 원본은 하나

**2.  컨테이너** : instance, 여러개의 컨테이너를 만들 수 있다 .

도커의 이미지는 원본은 하나이다. 하지만, 이미지로 여러개의 컨테이너를 만들 수 있다.

**컨테이너를 써야하는 이유 :** 독립성이 있고 , 확장성이 좋다 , 용량이 적게 드는 장점이 있다.

### **왜 리눅스에서 컨테이너를 돌리는가?**

리눅스 커널의 chroot,namespace,cgroup의 기능으로 컨테이너를 만들어줌

그럼 리눅스 운영체제에서만 가능한 거 같지만 window,mac os에서도

Hypervisor활성화시켜서 가능함

---

## virtualBox로 docker 실행하기

- Virtual Box 다운로드 후 설치

http://www.virtualbox.org/

- VirtualBox - Network 구성

NAT네트워크 추가 : 파일 - 환경설정 - 네트워크 - 추가

네트워크 이름 : localNetwork

네트워크 CIDR : 10.100.0.0/24

DHCP지원

- 포트포워딩

포트포워딩은 포트를 전달해 주는 것

![Untitled](https://user-images.githubusercontent.com/97584550/271455827-2a2e9afc-6c4b-4bcf-90d9-c89f43905421.png)

- ssh설정(원격로그인)
    - apt-get update && apt-get install -y openssh-server curl vim tree
- Xshell로 로그인구성

![Untitled1](https://user-images.githubusercontent.com/97584550/271455821-6d7c97e2-50c0-4a2c-bd68-2e16e6c18fcd.png)

```jsx
**docker desktop cmd명령어**

**docker pull postgres:lastest**  =  postgresql 최신 버전을 받을 수 있습니다.

**docker run  —name mariadb -v 공유 폴더 주소 :/var/lib/mysql -e  MARIADB_ROOT_PASSWORD=root -p 3306:3306 mariadb:10.7**

**—name** : 이름 설정

**-v** : 공유 폴더  

**-p** : 포트 번호 설정 앞은 host포트: docker 컨테이너 포트

**-d** : 백그라운드 모드 

**—rm** : 프로세스 종료시 컨테이너 자동 삭제

**-it** : input , terminal명령어를 사용함

**docker run --rm -it ubuntu:20.04 /bin/bash : cmd환경에서 우분투실행**
```

### Docker설치하기

- Centos와 Ubuntu에 docker설치

http://docs.docker.com/

- 설치방법
    - Repository를 이용해서 설치 (인터넷이 되는 경우)
    - download 후 직접설치
    - script를 이용한 설치
- 설치 후 동작 상태 확인

- 계정 추가

**docker는 관리자일때 쓸 수 있다.** 

그렇기때문에 guru나 일반 사용자일 경우에는 권한을 부여받아야한다.

guru → docker 관리자 권한 할당하는 명령어:

- ubuntuの場合
    - usermod -a -G docker guru(사용자 명)
- centos도 똑같음
    - -a : append -G : groub

systemctl status docker : 도커 상태를 볼 수 있다

centos는  docker - service가 disabled돼있기때문에 부팅시 실행이 안된다.

그래서 자동부팅설정을 해줘야함 

systemctl enable docker

---

## 컨테이너

컨테이너는 하나의 Application process

특징 : 완전하게 독립되서 움직인다.

docker host : docker demon이 움직인다, kernal은 하나

컨테이너 이미지는 여러개의 레이어로 구성을 해서 하나의 애플리케이션이 실행 할 수 있는 이미지 모음

container image는 단지 파일

실행되면 컨테이너화돼서 수정하거나 읽을 수 있는 프로세서가 된다

- [Hub.docker.com](http://Hub.docker.com) 에 node나 php, ubuntu등 10만여개의 데이터가 저장되어있다

$ docker search nginx

$ docker pull nginx:latest 이미지 가져오지

local로 hubDocker에 있는 이미지를 먼저 가져와야함

docker run -d —name web -p 80:80 nginx:latest

컨테이너 실행 순서

1. Docker Hub에서 컨테이너 이미지 검색
2. 컨테이너 이미지 다운로드 후 image layer보기
3. 컨테이너 실행하고 확인해보기

nginx이미지 다운받고 실행시킨 후 

확인하려면  **curl [localhost:80](http://localhost:80) 을 실행시키면 확인가능**

docker stop web(실행시킨 컨테이너 이름) 을 실행하면 컨테이너가 멈추고

docker rm web을 하면 컨테이서가 삭제되지만 이미지는 남아있음

rmi 는 이미지를 지우는것  ex)  docker rmi nginx

### 어떤것을 컨테이너를 만들까?

- 개발한 애플리케이션(실행파일)과 운영환경이 모두 들어있는 독립된 공간

ex) node hello.js가 있으면 nodejs도 있어야함

- 개발한 프로그램과 실행환경을 모두 컨테이너로 만듦
- MSA(Micro Service Architechture) 환경의 Polyglot애플리케이션 운영

### Dockerfile을 이용해 컨테이너를 빌드한다

- Dockerfile은
    - 쉽고, 간단, 명확한 구문을 가진 text file로 Top-down해석
    - 컨테이너 이미지를 생성할 수 있는 고유의 지시어를 가짐
    - 대소문자 구분하지 않으나 가독성을 위해 사용함
    - $ mkdir build
    - $ cd build
    - $ vi dockerfile
        
        FROM node: 12
        
        COPY hello.js /
        
        CMD [”node”,”/hello.js”]
        
    - $ docker build -t imagename:tag .
- Dockerfile문법
    - # : comment
    - FROM : 컨테이너의 BASE IMAGE(운영환경)
    - MAINTAINER : 이미지를 생성한 사람의 이름 및 정보
    - LABLE :컨테이너이미지에 컨테이너의 정보를 저장
    - **RUN 컨테이너 빌드를 위해 BASE IMAGE에서 실행할 commands**
    - **COPY 컨테이너 빌드시 호스트 파일을 컨테이너로 복사**
    - **ADD 컨테이너 빌드시 호스트 파일을 (tar,url포함) 을 컨테이너로 복사**
    - **WORKDIR 컨테이너 빌드시 명령어 실행될 작업 디렉터리 설정**
    - **ENV 환경변수 지정**
    - **USER 명령 및 컨테이너 실행시 적용할 유저 설정**
    - VOLUME 파일 또는 디렉토리를 컨테이너의 디렉토리로 마운트
    - EXPOSE 컨테이너 동작 시 외부에서 사용할 포트 지정
    - CMD 컨테이너 동작 시 자동으로 실행할 서비스나 스크립트 지정
    - ENTRYPOINT : CMD와 함께 사용하면서 command 지정 시 사용

### 컨테이너 배포하려면

$ docker build -t hellojs:latest

$ docker login : dockerHub의  아이디를 가입한다.

$ docker push hellojs:latest : 도커 허브로 배포된 컨테이너는 다른사람들이 쓸 수 있다.
