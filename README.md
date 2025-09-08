# 투두리스트 만들기

- JSP Model 2(디자인 패턴)
- JDBC

## 새 프로젝트 만들기

New Dynamic Web Project

1. Project name: "Memo"
2. Context root: "memo"
3. web.xml 체크

---
## 프로젝트 설정(파일구조, 라이브러리, DB)

#### DB

"script.sql"

#### 디렉토리 구조 & 파일 생성
- com.test.todo
  - Main.java : read, 시작 페이지
  - AddOk.java : create
  - CheckOk.java : update
  - DelOk.java : delete
- webapp/WEB-INF/views 폴더
  - main.jsp : 시작 페이지
  - addok.jsp : create
  - checkok.jsp : update
  - delok.jsp : delete
- **webapp/WEB-INF 폴더: 직원 전용 공간, 외부 사용자에게 공개되지 않는 폴더. 브라우저를 통해 절대 접근할 수 없음. jsp만 단독 실행되는 상황을 막기 위해..**

#### 라이브러리
- lib
  - ojdbc.jar
  - jstl-1.2.jar
  - lombok.jar
    - 이클립스 닫은 후 lombok.jar 더블클릭 → eclipse.exe 경로를 찾아서 추가하기
    - lib 폴더에도 복사해서 넣기

---
## 작업 순서
1. main.java
   1. annotation -> @WebServlet(value="/main.do")