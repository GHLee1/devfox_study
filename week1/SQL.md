# Database

SQL(Structured Query Language) : 데이터베이스 질의어

1)DML(Data Manipulation language) : 데이터베이스 관리 체계에 저장된 자료에 접근하고 조회하기 위해 사용되는 SQL문자

SELECT,INSERT,UPDATE,DELETE

```sql

select 조회컬럼 from 테이블명 where 조건

insert into 테이블명( 컬럼명,..)values (value,value...)

update 테이블명 set 수정할 컬럼명1=수정할 데이터,
										수정할 컬럼명2=수정할 데이터...
where 조건

delete from 테이블명 where 컬럼조건;
```

2)DDL(Data Definition Language) : 데이터베이스와 구성 요소를 정의 하기 위해 SQL 문장

CREATE,ALTER,DROP,TRUNCATE

```sql
create table 테이블명(컬럼명1 데이터타입 제약조건,
											컬럼명2 데이터타입 제약조건);

--테이블 컬럼 추가
  alter table member add password varchar2(50);

  --컬럼삭제
  alter table member drop COLUMN password;

  drop table member;

	--TRUNCATE table order_info; 전부삭제 롤백이 안됨.
	TRUNCATE table order_info;

```

3)TCL(Transaction Control Language) : 트렌젝션 데이터에 영구 저장 혹은 취소 명령어(commit,rollback)

4)DCL(Data control language) : 데이터베이스에 저장된 데이터를 여러 사용자가 무결성과 일관성을 유지하며 문제없이 공유할 수 있도록, 내부적으로 필요한 규칙이나 기법을 정의하는 데 사용하는 SQL문장

GRANT,REVOKE,COMMIT,ROLLBACK

**grant 권한어 to 계정명**
create user : 계정 생성 권한
drop user : 계정 삭제 권한
create session : 데이터베이스 접속 권한
create table
create sequence
create view
create procedure : 함수생성권한
resource : 저장공간을 사용할 수 있는 권한

SQL developer 계정만들기

1. cmd창에서 sqlplus /as sysdba 입력
2. create user 계정명 identified by 비밀번호;
3. grant connet,resource,dba to 계정명

oracle 데이터타입

문자열 : varchar2,char

숫자:number

날짜:date,timestamp

이진데이터 blob,clob

# join

1.INNER JOIN

2.LEFT OUTER JOIN

3.RIGHT OUTER JOIN

4.FULL OUTER JOIN

## 1.INNER JOIN

Inner join은 교집합연산과 같다. 조인 키 컬럼 값이 양쪽 테이블 데이터 집합에서 공통적으로 존재하는 데이터만 조인해서 결과 데이터 집합으로 추출

이너조인은 생략할 수 있고 ,콤마로 나타 낼 수 있다.

## LEFT OUTER JOIN

Left outer join은 교집합 연산결과와 차집합 연산 결과를 합친 것

키워드 왼쪽에 명시된 테이블에만 존재하는 데이터를 결과 데이터 집합으로 추출

```sql
SELECT 컬럼명 FROM 테이블 A
LEFT OUTER JOIN 테이블 B ON 테이블 A.조인키 컬럼
 = 테이블B. 조인키 컬럼;

OR

SELECT 컬럼명
FROM 테이블 A,테이블B
WHERE 테이블A.조인컬럼명 = 테이블B.조인키컬럼(+);

```

## RIGHT OUTER JOIN

right outer join = 오른쪽 교집합과 차집합의 합

```sql
SELECT 컬럼명 FROM 테이블 A
RIGHT OUTER JOIN 테이블 B ON 테이블 A.조인키 컬럼
 = 테이블B. 조인키 컬럼;

OR

SELECT 컬럼명
FROM 테이블 A,테이블B
WHERE 테이블A.조인컬럼명(+) = 테이블B.조인키컬럼;

```

## FULL OUTER JOIN

full outer join 은 합집합 연산 결과와 같습니다.

양쪽 테이블 조인 결과 모두를 데이터 집합으로 추출

SELECT 컬럼명 FROM 테이블 A

FULL OUTER JOIN 테이블 B ON 테이블A 조인키 = 테이블 B 조인키

## SELF JOIN

EMPLOYEES테이블에서 어떤 사원의 상사를 찾으라고 한다면

EMPLOYEES테이블에서 두번 비교를 해야한다.

이럴땐 ALIAS를 다르게 주고 비교할 수 있다.

```sql
SELECT A.EMPLOYSELECT A.EMPLOYEE_ID, A.FIRST_NAME,
       A.MANAGER_ID, B.EMPLOYEE_ID,
       B.FIRST_NAME
FROM EMPLOYEES A, EMPLOYEES B --- A : 사원 / B : 상사
WHERE A.MANAGER_ID = B.EMPLOYEE_ID
      AND A.EMPLOYEE_ID = 168;
```

## SUBQUERY

```sql
/* 서브쿼리 */
select * from employees e where salary >(select salary from employees e2
where first_name = 'Daniel');
-- 리사보다 입사일이 느린사람 모두의 정보출력
select * from employees e where e.hire_date > (select hire_date from employees e2 where first_name = 'Lisa');

select hire_date from employees where first_name = 'Lisa';
-- e2의 사원번호와  e의 매니저번호가 같은사람의 이름 출력
select employee_id,first_name,last_name,manager_id,(select first_name || ' ' || last_name from employees e2 where employee_id = e.manager_id)   as 서브쿼리
from employees e ;
--from subquery select문을 하나의 테이블로 본다.
select employee_id,first_name,email from
(select * from employees e where e.department_id = 50);
```
