# 리팩토링 Part 3 : 코드의 구린내

### 머릿말

앞선 두장에서는 리팩토링의 원리를 알아봤다. 하지만 원리를 안다고 해서 언제 적용해야 하는지를 파악하기는 쉽지 않다. 사실 함수가 몇 줄 이상일 때 혹은 인스턴스 변수가 몇 개 이상일 때 같은 판단 기준은 정답이 정해져 있지 않다. 연습을 통해 감을 잡아야 한다. 이런 리팩토링을 필요한 상황을 ‘구린내'라고 하겠다. 그렇다면 이제 감을 잡기 위한 연습 상황을 확인해보자.

---

### 중복 코드(Duplicate Code)

구린내의 제왕이다. 똑같은 코드 구조가 2개 이상일 때 하나로 통일하자.이 때는 메서드 추출 기법을 통해 별도의 메서드로 추출하면 된다.

---

### 장황한 메서드(Long Method)

좋은 프로그램을 살펴보면 공통적으로 갖는 특성들이 있다. 그 중 하나가 바로 짧은 메서드 길이다. 객체지향 프로그래밍을 할수록 느끼는 장점이 바로 작은 메서드다. 길고 긴 메서드와 주석으로 짜여진 코드보다는 해당 메서드의 의도를 정확히 반영하는 메서드명을 가진 짧은 메서드가 훨씬 좋다. 

또한 매개변수와 임시변수가 많을 경우도 역시 좋지 않다. 이럴 때에는 메서드 체인 기법과 임시변수를 메서드 호출로 전환하는 기법을 사용하면 해결할 수 있다. 조건문과 루프도 마찬가지다. 메서드로 빼자.

---

### 방대한 클래스

인스턴스 변수의 갯수가 수십개를 넘으며 수많은 기능이 존재하는 거대한 클래스들을 발견하면 절대 지나치지 말자. 이런 클래스에는 중복 코드가 반드시 존재하기 마련이다. 이 때 클래스 추출 기법을 실시하면 인스턴스 변수를 하나로 묶어 클래스로 빼낼 수 있다.

인스턴스 변수가 아닌 코드 분량 자체가 너무 많은 클래스도 중복 코드가 있을 가능성이 높다. 이 때도 중복 코드를 없애자. 이 때는 클래스 추출, 모듈 추출, 하위클래스 추출 기법 중 선택하면 된다. 

---

### 과다한 매개변수

매개변수가 많아진다는 것은 몇 가지 단점을 초래한다. 우선 매개변수끼리의 일관성이 없어질수도 있고, 필요한 데이터가 늘어나면 시그니처를 계속 수정해야 되기 때문에 매개변수의 의미를 이해하기도 힘들어진다. 따라서 이를 매개변수 세트로 전달하거나 객체로 전달하여 해결할 수 있다. 하지만 피호출 객체가 호출 객체에 의존하면 안될 때는 객체로 전달해선 안된다.

---

### 수정의 산발

수정의 산발은 하나의 클래스 안에서 여러개의 수정이 발생하는 것이다. 만약 클래스 A에서 특정 원인에 대한 대처로 내부의 여러 곳을 매번 수정한다면 이는 수정점들이 같은 성질을 가진다는 것을 의미한다. 따라서 이때는 수정된 부분들을 묶어 클래스 추출로 적용해 한 클래스로 빼내면 된다.

---

### 기능의 산재

기능의 산재는 하나의 수정으로 인해 여러 개의 클래스가 변경되는 경우를 말한다. 여러 개의 클래스에서 변경되는 부분들을 메서드 이동 혹은 필드 이동을 적용해 하나의 클래스에 넣으면 된다.

둘의 차이를 확실히 하고 가자. 수정의 산발은 하나의 클래스 안에서 여러 부분의 수정이 발생하는 것이고, 기능의 산재는 하나의 수정이 여러 클래스의 변경을 유발할 때 생기는 문제다. 둘 다 해결법은 하나의 수정이 하나의 클래스만 변경하도록 정리하면 된다.

---

### 잘못된 소속

자율적인 객체는 항상 스스로의 상태를 스스로 제어한다. 즉 데이터와 프로세스가 같은 곳에 위치한다. 하지만 이것이 위반되는 경우들이 있다. 클래스의 메서드들이 다른 클래스들의 데이터에 접근하는 경우다. 이 때는 접근하는 데이터 쪽으로 메서드 이동 기법을 통해 메서드가 제자리를 찾도록 변경해주면 된다. 만약 여러 클래스들에 접근한다면, 접근하는 데이터가 가장 많이 들어있는 클래스로 옮겨라.

---

### 데이터 뭉치

메서드 시그니처의 매개변수 혹은 클래스의 인스턴스 변수들은 동일한 항목들이 각각 합쳐진 경우들이 많다. 이 경우 뭉쳐진 항목들을 객체로 만들어줘야 한다. 이 때는 클래스 추출 기법을 적용하면 된다. 이후 매개변수로 작성된 부분들은 매개변수 세트를 객체로 전환 기법 혹은 객체를 통째로 전달 기법을 적용해 객체로 치환하면 된다. 

이 경우 다수의 매개변수가 객체 하나로 줄어들어 코드가 간결해지며 파편화된 값들을 객체로 묶을 수 있다는 장점도 있다.

---

### 강박적 기본 타입 사용

Java는 8개의 기본형 타입 클래스를 제공한다. 이 외에는 참조형 타입을 통해 우린 의미있는 데이터들을 별도의 클래스를 통해 군집화시킨다. 그런데 우린 이런 군집화라는 개념을 작은 개념이나 사소한 작업들에게는 잘 사용하지 않으려는 경향이 있다. 이럴 땐 과감하게 이런 생각을 집어던지고 데이터 값을 객체로 전환 기법을 통해 데이터들을 클래스로 변환하자. 이 외에도 뭉쳐 다녀야 할 여러 개의 필드가 있다면 클래스 추출 기법을 적용시켜 추출하자. 이는 과다한 매개변수에서도 사용했었다. 

---

### switch문

switch문은 반드시 중복을 유발한다. 또한 이리저리 파편화가 되어있을 때에는 수정할 때에도 문제가 있다. 이때는 객체지향의 다형성인 재정의를 이용하면 된다. 다른 방법으로 굳이 가려고 하지말고 바로 재정의를 실시하자. 

switch문은 보통 분기를 위해 분류 부호(type code)를 사용한다. 이 때 분류 부호란 객체 타입을 나타내는 값을 의미한다. 예를 들어 Car(1), Train(2), Plane(3) 같이 타입을 나타내기 위한 값이다. 보통 이땐 이 값들이 들어있는 메서드나 클래스가 있을텐데 이 때 메서드 추출을 실시한 후 메서드를 재정의해야 할 클래스에 옮겨넣으면 된다. 

이 후 다형성을 활용해 하위클래스나 상태/전략 패턴으로 전환 기법으로 분류 부호를 상속 구조로 변환하고 재정의로 전환하면 된다. 물론 case문의 복잡성이 크지 않은 경우는 다형성을 활용하는 것보다는 매개변수를 메서드로 전환하자.

---

### 직무유기 클래스

클래스는 단순히 생성되는 것 외에도 유지관리와 이해에 필요한 리소스가 필요하다. 따라서 기능이 축소되거나 쓸모없어지는 등의 효율성이 떨어지는 클래스는 삭제하자. 효율이 떨어지는 하위 클래스나 모듈에는 계층 병합을, 사용되지 않는 구성 요소들은 클래스 내용, 모듈 내용 직접 삽입을 사용하면 된다.

---

### 막연한 범용 코드

범용 코드란 어디서나 쓸수있는 코드다. ‘음, 왠지 곧 이 기능이 필요할 것 같은데' 라고 생각이 들며 추가되는 막연한 코드다. 이 코드들은 대부분 필요없는 기능이기 때문에 대체로 정확한 요구사항을 반영하지 않기 때문에 유지보수하기 어려워진다. 따라서 별다른 기능이 없는 클래스, 모듈은 계층 병합을 통해 하나의 클래스로 합치면 된다.

---

### 임시 필드

보통 우리들은 객체를 생성하고 책임을 할당할 때 객체가 가진 인스턴스 변수를 이용하리라 믿는다. 하지만 특정 상황에서만 할당되는 경우가 간혹 있는데 이 때는 이 변수를 클래스 추출을 실시해 빼내줘야 한다.

---

### 메시지 체인

메시지 체인은 클라이언트의 요청이 객체에서 객체로 여러 객체에게 연쇄적으로 요청되는 문제점을 말한다. 이 때는 대리 객체 은폐를 실시하면 된다. 예를 들어 A 객체가 B에게 요청하는 결과값이 C에 있다고 가정했을 때 우리는 메서드 체인을 사용해 A.getParent().getChild() 형태로 작성한다. 이를 A.getChild() 형태로 변경하고 A의 메서드 안에 getParent().getChilde()를 구현하는 것이 대리 객체 은폐다.

이 기법은 메서드 체인을 구성하는 모든 객체에게 적용이 가능하다. 하지만 이는 모든 중간 객체가 중개 메서드로 변환되어 과잉 중개 메서드가 되버린다. 따라서 이 때는 객체가 어느 대상에 사용되는지 알아내 해당 코드 부분을 메서드 추출로 뺴낸 뒤 이동시켜 체인 아래로 밀어낼 수 있는지 검사하는 것이 좋다. 

---

### 과잉 중개 메서드

객체지향의 핵심은 캡슐화다. 이를 통해 자세한 세부사항을 알지 않고도 다른 객체와의 협력을 통해 처리할 수 있다. 하지만 이 역시 과유불급이다. 대부분의 메서드가 다른 클래스에 기능을 위임하고 있다면 과잉 중개 메서드를 실시할 타이밍이다. 

---

### 지나친 관여

클래스끼리 너무 깊은 관여를 해서는 안된다. 지나친 관여가 의심된다면 각 클래스를 분리해 이를 줄여야 한다. 클래스의 양방향 연결을 단방향으로 전환하는 것이 가능하다면, 공통 부분은 클래스 추출을 통해 빼내고 대리 객체 은폐를 통해 둘의 깊은 관여를 끊어내야 한다.

---

### 인터페이스가 다른 대용 클래스

기능이 같지만 메서드의 시그니처가 각기 다를 땐 메서드명 변경을 실시하자.

---

### 데이터 클래스

데이터 클래스는 객체가 구성이 필드와 필드의 getter,setter 메서드만 존재하는 클래스를 말한다. 이 클래스의 존재 목적은 단지 ‘데이터 보관'뿐이다. 객체의 상태를 제어하거나 조회하는 등의 구체적인 데이터 조작은 다른 클래스가 수행한다. 사실 이 경우 객체의 존재 의미는 거의 없다고 보는 것이 맞다. 본문에선 객체라는 이름조차 아까운 멍청한 클래스(dumb class)라고 표현한다. 

이 경우 이런 메서드들이 다른 클래스에 사용되고 있다면 메서드 이동을 통해 해당 기능을 그쪽으로 옮겨야 한다.