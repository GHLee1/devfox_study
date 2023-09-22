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