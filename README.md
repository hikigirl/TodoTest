# 투두리스트 만들기

- JSP Model 2(디자인 패턴)
- JDBC

## 1. 새 프로젝트 생성

New Dynamic Web Project

1. Project name: "Todo"
2. Context root: "todo"
3. web.xml 체크
4. 환경: Tomcat 9

---
## 2. 프로젝트 설정(파일구조, 라이브러리, DB)

#### DB

"script.sql"

#### 디렉토리 구조 & 파일 생성
- com.test.todo
  - Main.java : read, 시작 페이지
  - AddOk.java : create
  - CheckOk.java : update
  - DelOk.java : delete
- com.test.todo.model
  - TodoDAO.java
  - TodoDTO.java
  - DBUtil.java(복사)
- webapp/WEB-INF/views 폴더
  - main.jsp : 시작 페이지
  - addok.jsp : create
  - checkok.jsp : update
  - delok.jsp : delete
- **webapp/WEB-INF 폴더: 직원 전용 공간, 외부 사용자에게 공개되지 않는 폴더. 브라우저를 통해 절대 접근할 수 없음. jsp만 단독 실행되는 상황을 막기 위해..**

#### 라이브러리
- lib 폴더
  - ojdbc.jar
  - jstl-1.2.jar
  - lombok.jar
    - 이클립스 닫은 후 lombok.jar 더블클릭 → eclipse.exe 경로를 찾아서 추가하기
    - lib 폴더에도 복사해서 넣기

---

## **추가 필기

#### 405 에러

- GET 방식 -> doGet 호출
- POST 방식 -> doPost 호출

---

## 3. 작업 순서

- 어떤 객체의 업무가 증가 -> 코드 관리 불편 & 가독성 저하
- 업무를 분리(각각의 클래스 생성)
  - 일반 업무 -> XXXService 클래스 (ex. FileService.java, CalcService.java)
  - 데이터(DB) 업무
    - XXXDAO 클래스(Data Access Object)
    - XXXDTO 클래스(Data Transfer Object)
      - 계층간 데이터를 전송
      - 테이블의 레코드 1줄을 담는 상자
    - MVC 패턴에서 Model에 해당함

1. main.java
    1. annotation -> @WebServlet(value="/main.do") : 가상 주소 xml에 만드는 대신 입력
    2. HttpServlet 상속받아 requestdispatcher 등등..
2. main.jsp
    1. 기본 구조 잡기
    2. 데이터 넘길 때 .jsp가 아닌 서블릿(가상주소)를 불러야 함
3. main.java
    1. DB 작업 -> select로 가져옴
    2. 결과셋 -> 전달 -> jsp 호출
4. TodoDTO.java
    1. 멤버 변수는 private(멤버 변수는 DB 테이블에 대응시켜 만드는 편)
    2. getter, setter를 구현한다.
    3. 나머지 자유 구현(toString 등)
5. TodoDAO.java
    1. 모든 데이터 조작하는 코드 담당(JDBC)
    2. 각 업무별 메서드 구현(select, insert, update, delete)
        1. DB에서 얻어온 resultset을 Arraylist에 복사
6. main.java
    1. TodoDAO 객체 생성 후 메서드 호출
    2. request에 list를 담아서 jsp로 전송
7. main.jsp
    1. request로부터 받아온 값을 EL, JSTL을 활용해 화면에 출력
8. AddOk.java
    1. 데이터 가져오기(todo)
    2. DB작업 - insert
    3. JSP 호출
9. TodoDAO.java
    1. add()메서드 작성
10. addok.jsp
    1. 결과값에 따른 처리
11. main.jsp
    1. 체크박스에 이벤트 걸기
    2. 사용자 정의 데이터 data-seq에 기본키 넣기
    3. checkok.do에 데이터 전달(get + 쿼리스트링)
12. CheckOk.java
    1. 데이터 가져오기(seq, state)
    2. DB작업 -> update
        1. 계층 간 데이터를 주고받는 일
        2. 최대한 단순하게 주고받기(하나만 넘기고 하나만 받기)
        3. 매개변수
        4. 리턴값(유일)
        5. 매개변수가 두개 이상인 경우 => 상자 역할인 DTO 객체에 담아서 전달하자
    3. JSP 호출
13. TodoDAO.java
    1. check() 메서드 작성
14. checkok.jsp
    1. addok.jsp에 작성한 자바스크립트와 동일한 내용 작성
    2. checkOk.java에서 addok.jsp를 불러도 동일한 결과(서블릿과 jsp가 꼭 일대일 관계여야 하는 것이 아님)
15. main.jsp
    1. 체크표시하면 체크표시 유지, 취소선 생기게끔 작성
    2. 할일 텍스트를 클릭해도 체크표시가 되게끔
16. main.jsp
    1. 삭제 이벤트 걸고 delok.do로 이동, 어떤 할일을 삭제해야 하는지 전달 필요
    2. seq를 넘겨줘야 함
17. DelOk.java
    1. 데이터 가져오기(seq)
    2. DB작업 -> delete
    3. JSP 호출
18. TodoDAO.java
    1. del() 메서드 작성
19. delok.jsp
    1. 결과값에 따른 처리