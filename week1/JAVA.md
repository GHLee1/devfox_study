# JAVA

JDK : JAVA Development kit(자바 개발 키트)

JRE: Java Runtime Environment(자바 실행 환경) = JVM이 자바 프로그램을 실행하기위해 필요한

라이브러리와 파일들을 가지고 있다.

JVM: Java Virtual Machine = java파일을 class로 컴파일함 , 사용자와 os사이에 있고 java os같은 역할을 한다.

자바는 정수형(int,byte,short,long) 기본형타입중

byte+byte더하면 기본형 int로 바꿔주기 때문에 형변환을 해야함

short+short도 기본형int로 바꿔서 더 하기 때문에 형변환을 해줘야함

```java
byte b1 = 5;
byte b2 = 10;
byte b3;
b3 = b1+b2; //xx
b3 = (byte)(b1+b2);// byte로 형변환 해야함
```

### class - 자바에서 프로그램을 실행하기 위한 최소 단위

클래스의 멤버: 필드(멤버 변수),메소드,생성자

-필드 : 클래스 안에 있는 변수(Variable)라고 한다.

-지역 변수 : 메소드 안에 있는 변수(local variable)라고 함.

-메소드 : 행동(main)

클래스의 정의 문법

[접근제어자] class 클래스명 {}

접근제어자 종류

-public, private, protected, default

**static을 사용하면 바로 메모리에 할당되기 때문에 객체화를 하지 않고 바로 사용할 수 있다.**

파라미터(Parameter) = 객체화를 한 뒤 메소드를 호출하면서 보내는 것은 파라미터

아규먼트(Argument) = 메소드에서 받아온 걸로 정의해서 사용하는 것은 아규먼트다.

- 아규먼트테스트

  ```java
  public class ArgumentTest {
    // 메소드
    public String test_method(String send) {
      // 접근제어자 뒤에 String은 리턴값 메소드명뒤 괄호안에 값은 Argument
      System.out.println("method inside");
      String sendValue = send + "값 들어옴";
      return sendValue;
    }

    public static void main(String[] args) {
      // TODO Auto-generated method stub
      ArgumentTest me = new ArgumentTest();
      System.out.println(me.test_method("파라미터"));
    }

  }

  출력 : method inside
  			파라미터값 들어옴
  ```

메소드 정의 문법

접근제어자 [static] 리턴타입 메소드명 (매개변수){}

```java
package test;

public class ScopeTest { // Class 영역
	String sClassVal = "Class Value";
// Class 영역에 선언한 변수(Global Variable)

static String sStaticVal = "Static Value";
// 스태틱은 클래스 내에서 공유되어 아무데서나 사용 가능

	public void method1() { // 메소드 영역
		String sMethod1Val = "method1 Value";
		// Method 영역에 선언한 변수(Local Variable)
		System.out.println(sClassVal);
		// Class 영역 안에 있는 메소드에서는 클래스 변수 사용 가능
	} // 메소드 영역

	public static void main(String[] args) {
		// 메인 메소드는 static 변수가 아닐 경우 객체화해야 클래스 변수 사용 가능
		System.out.println(sStaticVal);
		ScopeTest s = new ScopeTest();
		System.out.println(s.sClassVal);
	}

} // Class 영역 끝
```

배열 문법

1.배열은 데이터를 삭제하면 해당 인덱스 주변의 데이터들을 이동시켜줘야함

2.배열의 크기는 선언할 때 지정할 수 있으며, 한 번 선언하면 크기를 늘리거나 줄일 수 없다.

3.구조가 간단하므로 코딩 테스트에 많이 사용한다.

배열은

데이터타입[] 배열명 = new 데이터타입[크기];

                          or

데이터타입 배열명[] = new 데이터타입[크기];

으로 선언이 가능하다

```java
public class ArrayTest {

  public static void main(String[] args) {
    int[] arrCase1 = new int[3];
    int arrCase2[] = new int[3];

    arrCase1[0] = 10;
    arrCase2[2] = 20; // 배열에 값 넣어 초기화
    System.out.println("arrCase1[0] :" + arrCase1[0]);
    System.out.println("arrCase2[2] :" + arrCase2[0]);

    String[] arrCase3 = {"A", "B", "C"};
    for (String string : arrCase3) {
      System.out.print(string);
    } // foreach for(타입 변수명: 배열){반복할 문장}
    System.out.println("digital export");
  }
}
```

리스트 문법

리스트는 값과 프린터를 묶은 노드라는 것을 포인터로 연결한 자료구조.

크기 변하기 쉬운 데이터 구조에 사용함

List는 자바의 자료형 중 하나로 배열과 비슷하지만

결정적으로 다른 점은

1. 크기를 자유롭게 설정 가능
2. 배열은 직접 액세스,순차 액세스 모두 가능 list는 순차 액세스만 가능

### 리스트 Code

```java
public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>(); // List 선언(ArrayList)
		LinkedList<String> list2 = new LinkedList<String>(); // List 선언(LinkedList)

		list2.add("E");
		list.add("A");
		list.add("B");
		list.add("C"); // List 추가
		list.add(0, "D"); // 0번째에 D값을 추가(동일한 값이 있을 경우 밀어냄)
		System.out.println("List 값 확인 : " + list);
		System.out.println("List 인덱스 값 확인 : " + list.get(0));

		list.remove(2); // List 삭제(인덱스)
		list.remove("B"); // List 삭제(값으로)

		list.set(0, "Z"); // List 값 변경 (인덱스, "변경할 값")

		System.out.println("List 크기 확인 : " + list.size());

		System.out.println("List 안에 특정 값 들었는지 확인 : " + list.contains("B"));

		System.out.println("List 안에 아무것도 들지 않았는지 확인 : " + list.isEmpty());

		list.addAll(list2); // 리스트에 다른 리스트 더하기
		String[] arr = {"ARRAY"};
		list.addAll(Arrays.asList(arr)); // 배열을 리스트로 더하기
		System.out.println("List 안에 다른 리스트 더하기 : " + list);
	}
```

### 오버라이딩과 오버로드 정의

오버라이딩은 자식클래스에서 부모클래스를 상속받을 때 똑같은 이름의 메소드가 있을 때 자식(내클래스) 메소드로 호출되는 것

오버로딩는 동일한 메소드명으로 받는 파라미터를 다르게 해서 작성 가능한 것

**@Override** - toString메소드 는 class 최상위 object한테 toString이라는 메소드를 상속받아 사용함.

## String 주소값비교

```java
String str1 = "Hello";
String str2 = "Hello";
System.out.println(str1==str2); // true가 출력됨

String str3 = new String("Hello");
String str4 = new String("Helllo");
System.out.println(str3==str4); //false가 출력됨

System.out.println(str3.contains(str4)); //true contains는 "He"만되도 true가 됨
System.out.println(str3.equals(str4)); //true

```

## 상속

1.단일상속 : 부모가 하나

2.다중상속 : 부모가 여러개

![img11](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fcgx4i5%2FbtrN4OZMlUj%2FHf0a1a0KzKQ2u5NEtzHgKk%2Fimg.png)

자바에서 자식클래스의 부모가 2개가 올 수 없다.

```java
class Car {
  String name;
  int speed;
  String color;

  public Car(String name) {
    this.name = name;
  }

  public void showInfo() {
    System.out.println("name : " + name);
    System.out.println("speed : " + speed);
    System.out.println("color : " + color);
  }

}

class Bus extends Car {
  int max; // 버스 인원제한 수

  public Bus(String name, int max) {
    super(name);
    this.max = max;
  }

  @Override
  public void showInfo() {
    // System.out.println("name : " + name);
    // System.out.println("speed : " + speed);
    // System.out.println("color : " + color);
    super.showInfo();
    System.out.println("max : " + max);
  }

  public void showMax() {
    System.out.println("맥스값" + max);
  }
}

class Truck extends Car {
  int capacity; // 최대 적재량

  public Truck(String name, int capacity) {
    super(name);
    this.capacity = capacity;
  }

  @Override
  public void showInfo() {
    super.showInfo();
    System.out.println("capacity : " + capacity);
  }
}

public class CarMain {

	public static void main(String[] args){
		 Bus bus = new Bus("타요 버스", 30);
		 Truck truck = new Truck("봉고", 1000);
		 bus.color = "파란색";
		 bus.speed = 101;
		 truck.color = "빨간색";
		 truck.speed = 90;
		 bus.showInfo();
		 System.out.println();
		 truck.showInfo();

		/*  객체지향 프로그램의 특징 3가지
     *  1.Encapsulation(캡슐화)
     *  2.Inheritance(상속)
     *  3.Polymorphism(다형성)
     */

		// 다형성(Polymorphism)
    // 묵시적형변환 car가 bus의 부모라 타입이 더 크기 때문에 묵시적 형변환이 된다.
    // 다형적 데이터 타입(상속관계에서의 형변환)
    Car car1 = new Bus("마을버스", 15);
    Car car2 = new Truck("화물트럭", 2500);
    // 명시적형변환
    System.out.println(((Truck) car2).capacity);
    car1.showInfo();
    car2.showInfo();
    // 자식이 들고 있는 고유의 메소드를 불러오고 싶으면 강제형변환을 해줘야함
    Bus bus1 = (Bus) car1;
    bus1.showMax();

	}


}
```

# Collection

# List

-순서가 없다

-중복을 허용한다.

```java
public class ArrayListEx1 {

  public static void main(String[] args) {
    // list.add => 값추가 .get 인덱스에 저장된 값 들고오기
    List<String> arr1 = new ArrayList<String>();
    arr1.add("김계똥");
    arr1.add("이건희");
    arr1.add(2, "김건");
    // arr1.remove(0);
    System.out.println(arr1);

  }
}
```

# Set

-순서가 없다

-중복을 허용하지 않는다.(집합)

```java
package collection;

import java.util.HashSet;
import java.util.Iterator;

class Person {
  String name;
  int age;

  // @Override
  // public int hashCode() {
  // final int prime = 31;
  // int result = 1;
  // result = prime * result + age;
  // result = prime * result + ((name == null) ? 0 : name.hashCode());
  // return result;
  // }
  //

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  @Override
  public String toString() {
    return "Person [name=" + name + ", age=" + age + "]";
  }
}

public class HashSetEx1 {

  public static void main(String[] args) {
    /*
     * Set
     * -순서를 보장하지 않는다.
     * -중복을 허용하지 않는다.
     */

    HashSet<Person> hset = new HashSet<>();
    // 엘리먼트 추가
    Person p = new Person("이건희", 2);
    hset.add(new Person("홍길동", 3));
    hset.add(new Person("김개똥", 19));
    hset.add(p);
    hset.add(p);
    hset.remove(p);

    // set출력
		//set을 Iterator안에 담기
    Iterator<Person> iter = hset.iterator();

    while (iter.hasNext()) {//iterator에 다음 값이 있다면
      System.out.println(iter.next()); //iter에서 값 꺼내기
    }

  }
}
```

---

# Map

-키와 값의 쌍으로 데이터를 저장

-키는 중복을 허용하지 않는다.

-값은 중복을 허용한다.

```java
package collection;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapEx1 {
  public static void main(String[] args) {
    Map<Integer, String> hmap = new HashMap<>();
    // 엘리먼트 추가 (put)
    hmap.put(1, "영식");
    hmap.put(2, "영철");
    // 키 엘리먼트 읽기 (get)
    System.out.println(hmap.get(1));
    System.out.println(hmap.get(2));
    // 기존에 있던 2번키에 새로운 값을 넣으면 2번키에 값을 덮어씀.
    hmap.put(2, "정숙");
    System.out.println(hmap.get(2));

    System.out.println(hmap.size());
    // 키값만 읽기 키값은 set으로 저장돼있다.
    Set<Integer> keys = hmap.keySet();
    System.out.println(keys);
    for (Integer key : keys) {
      System.out.println(key);
    }
    // 값만 읽기
    Collection<String> values = hmap.values();
    for (String value : values) {
      System.out.println(value);
    }

    // 값삭제
    hmap.remove(1);
    System.out.println(hmap.get(1));
  }
}
```

```java
package collection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

class MemberIndex {
  // 객체를 만들 필요없이 클래스명으로 바로 호출가능
  private static long index;

  public static long makeIndex() {
    // 전치증가
    return ++index;
  }

}

class Member {
  String name;
  int age;

  public Member(String name, int age) {
    this.name = name;
    this.age = age;
  }

  @Override
  public String toString() {
    return "Member [name=" + name + ", age=" + age + "]";
  }

}

public class HashMapEx2 {
  public static void main(String[] args) {

    Map<Long, Member> memberMap = new HashMap<Long, Member>();
    memberMap.put(MemberIndex.makeIndex(), new Member("정숙", 45));
    memberMap.put(MemberIndex.makeIndex(), new Member("영철", 42));
    memberMap.put(MemberIndex.makeIndex(), new Member("영수", 46));
    // System.out.println(memberMap.size());
    // System.out.println(memberMap.get(1L).name);

    // System.out.println(memberMap.keySet());
    Set<Long> keys = memberMap.keySet();
    for (Long key : keys) {
      System.out.println(memberMap.get(key).toString());
    }
  }
}
```
